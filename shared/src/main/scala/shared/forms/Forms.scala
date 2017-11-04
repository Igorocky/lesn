package shared.forms

import shared.dto.{User, UserFull}
import shared.forms.Validations._


object Forms {
  type SubmitResponse[F,S] = Either[FormData[F],S]

  lazy val createUserForm = new FormMethods[UserFull] {
    val login = field("Login", _.login)(nonEmpty)
    end
  }

}
