package app

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.extra.Reusability
import shared.dto.User

object Reusabilities {
  implicit val userReuse = Reusability.byRef[User]
  implicit val callbackReuse = Reusability[Callback](_ == _)
  implicit val userToCallbackReuse = Reusability[User => Callback](_ == _)
}
