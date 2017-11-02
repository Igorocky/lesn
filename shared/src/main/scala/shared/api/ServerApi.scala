package shared.api

trait ServerApi {
  def logIn(login: String, pass: String): Either[String, String]
}
