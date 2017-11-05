package shared.api

import shared.dto.{CreateUserRequest, User}
import shared.forms.Forms.SubmitResponse

trait ServerApi {
  def logIn(login: String, pass: String): Either[String, User]
  def createUser(user: CreateUserRequest): SubmitResponse[CreateUserRequest,User]
  def listUsers(u: Unit): Either[String, List[User]]
}
