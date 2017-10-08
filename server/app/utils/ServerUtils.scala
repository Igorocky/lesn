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

  def bundleUrl(projectName: String): String = {
    val name = projectName.toLowerCase
    Seq(s"$name-opt-bundle.js", s"$name-fastopt-bundle.js", s"$name-bundle.js")
      .find(name => getClass.getResource(s"/public/$name") != null)
      .map(controllers.routes.Assets.versioned(_).url)
      .get
  }
}
