package controllers

import javax.inject._

import app.RouterBuilderUtils
import shared.api.ServerApi
import shared.dto._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ServerApiImpl @Inject()(val configuration: play.api.Configuration
                            )(implicit private val ec: ExecutionContext) extends RouterBuilderUtils[ServerApi] {

  val router: Router = RouterBuilder()

    .addHandlerWithSessionMod(forMethod2(_.logIn)) {
      case (session, (login, pass)) =>
        Future.successful(
          if (login == "adm" && pass == "123") {
            (session.copy(sessionId = "new-id", userId = 5), Right(User(login = "adm", role = UserRole.Admin)))
          } else {
            (session, Left("Invalid login or password"))
          }
        )
    }

    .addHandlerWithSession(forMethod(_.listUsers)) {
      case (session, _) =>
        Future.successful(Right(
          List(User(id = Some(1), login = "LLDD", role = UserRole.Admin))
        ))
    }

}
