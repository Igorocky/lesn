package utils

import play.api.i18n.Lang
import play.api.mvc._
import shared.messages.Languages.{EN, RU}
import upickle.default._

object ServerUtils {
  def getDefaultLanguage(acceptLanguages: Seq[Lang]) = acceptLanguages.toList match {
    case Nil => EN
    case locale :: tail => locale.code match {
      case "ru-RU" => RU
      case "ru" => RU
      case "en-US" => EN
      case "en" => EN
      case _ => EN
    }
  }

  def getSession(implicit requestHeader: RequestHeader): Session =
    requestHeader.session.get(Session.SESSION)
      .map(read[Session])
      .getOrElse(Session(language = getDefaultLanguage(requestHeader.acceptLanguages)))

  def modSession(s: Session): (String, String) = {
    (Session.SESSION -> write(s))
  }

  private val possibilities = List("fastopt", "opt")
  private def exactlyOneOf[E](seq: Seq[E], selector: E => Boolean) = seq.find(selector).get
  private def mustHaveSequences(prefix: String):Seq[Seq[String]] = List(
    possibilities.map(mid => s"$prefix-$mid-library.js")
    ,possibilities.map(mid => s"$prefix-$mid-loader.js")
    ,possibilities.map(mid => s"$prefix-$mid.js")
  )
  private def resExists(name: String) = getClass.getResource(s"/public/$name") != null
  def scriptUrls(projectName: String): Seq[String] =
    mustHaveSequences(projectName.toLowerCase)
      .map(exactlyOneOf(_, resExists))
      .map(controllers.routes.Assets.versioned(_).url)
}
