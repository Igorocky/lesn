package shared.api

import shared.dto.{User, UserRole}

trait ServerApi {
  def logIn(login: String, pass: String): Either[String, User]
  def createUser(login: String, pass: String, role: UserRole): Either[String, User]
  def listUsers(u: Unit): Either[String, List[User]]
}
