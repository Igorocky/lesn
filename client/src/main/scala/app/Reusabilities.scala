package app

import japgolly.scalajs.react.extra.Reusability
import shared.dto.User

object Reusabilities {
  implicit val userReuse = Reusability.byRef[User]
}
