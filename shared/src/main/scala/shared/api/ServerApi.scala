package shared.api

import shared.dto.{User, UserFull, UserRole}
import shared.forms.Forms.SubmitResponse

trait ServerApi {
  def logIn(login: String, pass: String): Either[String, User]
  def createUser(user: UserFull): SubmitResponse[UserFull,User]
  def listUsers(u: Unit): Either[String, List[User]]
}
