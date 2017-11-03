package controllers

import javax.inject._

import akka.actor.ActorSystem
import db.PrintSchema
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import play.api.{Environment, Logger}
import shared.api.ThePageParams
import slick.jdbc.JdbcProfile
import upickle.default.{read, write}
import utils.ServerUtils.getSessionId
import utils.{Session, SessionStorage}

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 *
 * @param cc standard controller components
 * @param actorSystem We need the `ActorSystem`'s `Scheduler` to
 * run code after a delay.
 * @param exec We need an `ExecutionContext` to execute our
 * asynchronous code.  When rendering content, you should use Play's
 * default execution context, which is dependency injected.  If you are
 * using blocking operations, such as database or network access, then you should
 * use a different custom execution context that has a thread pool configured for
 * a blocking API.
 */
@Singleton
class MainController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem,
                               val wsRouter: Router,
                               val sessionStorage: SessionStorage,
                               protected val dbConfigProvider: DatabaseConfigProvider)
                              (implicit private val environment: Environment,
                               implicit private val exec: ExecutionContext) extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  new PrintSchema()

  def app = Action.async {
    Future.successful(Ok(views.html.univpage(
      customData = write(ThePageParams(
        wsEntryUrl = routes.MainController.wsEntry.url
      )),
      pageTitle = "univpage"
    )))
  }

  def wsEntry = postRequest(read[(String, String)]) {
    case (sessionIdOpt, (path, input)) =>
      val sessionBefore = sessionStorage.get(sessionIdOpt)
      Logger.debug(s"wsEntry.input: sessionBefore: '$sessionBefore', path: '$path', input: '$input'")
      wsRouter.handle(path, sessionBefore, input)
        .map(_.map{case (sessionAfter, res) =>
          Logger.debug(s"wsEntry.output: sessionBefore: '$sessionBefore', sessionAfter: '$sessionAfter', path: '$path', input: '$input', result: '$res'")
          val resp = Ok(res)
          if (sessionAfter.userId > 0) {
            sessionStorage.remove(sessionIdOpt)
            sessionStorage.remove(sessionBefore.sessionId)
            sessionStorage.put(sessionAfter.sessionId, sessionAfter)
            resp.withCookies(Cookie(Session.SESSION_ID, sessionAfter.sessionId))
          } else {
            sessionStorage.remove(sessionIdOpt)
            sessionStorage.remove(sessionBefore.sessionId)
            sessionStorage.remove(sessionAfter.sessionId)
            resp.discardingCookies(DiscardingCookie(Session.SESSION_ID))
          }
        }).getOrElse {
        val msg = s"No handler found for path '$path'"
        Logger.error(msg)
        Future.successful(BadRequest(msg))
      }
  }

  private def postRequest[T](parser: String => T)(action: (Option[String], T) => Future[Result]) = Action.async {implicit request =>
    action(getSessionId, parser(request.body.asText.get))
  }

}
