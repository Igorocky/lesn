package app.actions

import app.components.custom.{LoginFormActions, WindowFunc}
import japgolly.scalajs.react.Callback
import shared.dto.User

trait LoginFormActionsImpl extends LoginFormActions {self: WindowFunc with GlobalContext =>
  override def logIn(login: String, pass: String)(cb: Either[String, User] => Callback) =
    serverApi.post(_.logIn(login, pass), showError){
      case res @ Right(loggedInAs) => modLoggedInAs(_ => Some(loggedInAs)) >> cb(res)
      case res => modLoggedInAs(_ => None) >> cb(res)
    }
}
