package shared.forms

import shared.dto.CreateUserRequest
import shared.forms.Validations._
import shared.messages.Messages


object Forms {
  type SubmitResponse[F,S] = Either[FormData[F],S]

  lazy val createUserForm = new FormMethods[CreateUserRequest] {
    val login = field("Login", _.login)(nonEmpty)
    val role = field("Role", _.role)(none)
    val password = field("Password", _.password)(nonEmpty)
    val repeatPassword = field("Repeat password", _.repeatPassword)(nonEmpty)
    end

    override protected def commonValidations = List(
      fd =>
        if (password.get(fd) != repeatPassword.get(fd))
          Some(Messages.fieldsShouldHaveSameValue(password.label, repeatPassword.label)(_))
        else None
    )
  }

}
