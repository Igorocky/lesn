package shared.forms

import shared.dto.{CreateUserRequest, User, UserFull}
import shared.forms.Validations._


object Forms {
  type SubmitResponse[F,S] = Either[FormData[F],S]

  lazy val createUserForm = new FormMethods[CreateUserRequest] {
    val login = field("Login", _.login)(nonEmpty)
    val role = field("Role", _.role)(none)
    end
  }

}
