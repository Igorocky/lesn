package shared.dto

import shared.messages.{Language, Languages}

case class UserRole(code: Int) {
  def toStr = code match {
    case 0 => "Admin"
    case 1 => "Teacher"
    case 2 => "Pupil"
  }
}

object UserRole {
  final val Admin = UserRole(0)
  final val Teacher = UserRole(1)
  final val Pupil = UserRole(2)

  private final val ALL_ROLES = List(Admin, Teacher, Pupil)

  def fromInt(code: Int): UserRole = ALL_ROLES.find(_.code == code).get
}

case class UserFull(id: Option[Long] = None,
                role: UserRole = UserRole.Pupil,
                login: String = "",
                language: Language = Languages.EN,
                salt: String = "",
                passHash: String = ""
               )

case class User(id: Option[Long] = None,
                role: UserRole = UserRole.Pupil,
                login: String = "",
                language: Language = Languages.EN
               )

case class CreateUserRequest(login: String = "",
                             role: UserRole = UserRole.Pupil,
                             password: String = ""
                            )