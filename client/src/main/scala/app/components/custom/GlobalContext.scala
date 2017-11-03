package app.components.custom

import app.WsClient
import japgolly.scalajs.react.Callback
import shared.api.ServerApi
import shared.dto._

case class GlobalMem(loggedInAs: Option[User] = None)

trait GlobalContext extends LoginFormActions with WindowFunc {

  //abstract members
  val serverApi: WsClient[ServerApi]

  protected def modGlobalMem(f: GlobalMem => GlobalMem): Callback

  private def mod(f: GlobalMem => GlobalMem): Callback = modGlobalMem(f)

  //actions
  override def logIn(login: String, pass: String)(cb: Either[String, User] => Callback) =
    serverApi.post(_.logIn(login, pass), showError){
      case res @ Right(loggedInAs) => mod(_.copy(loggedInAs = Some(loggedInAs))) >> cb(res)
      case res => mod(_.copy(loggedInAs = None)) >> cb(res)
    }
}
