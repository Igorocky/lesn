package app.components.custom

import app.WsClient
import japgolly.scalajs.react.Callback
import shared.api.ServerApi

case class GlobalMem(loggedInAs: Option[String] = None)

trait GlobalContext extends LoginFormActions with WindowFunc {

  //abstract members
  val serverApi: WsClient[ServerApi]

  protected def modGlobalMem(f: GlobalMem => GlobalMem): Callback

  private def mod(f: GlobalMem => GlobalMem): Callback = modGlobalMem(f)

  //actions
  override def logIn(login: String, pass: String)(cb: Either[String, String] => Callback) =
    serverApi.post(_.logIn(login, pass), showError){
      case res @ Right(loggedInAs) => mod(_.copy(loggedInAs = Some(loggedInAs))) >> cb(res)
      case res @ Left(errMsg) => mod(_.copy(loggedInAs = None)) >> cb(res)
    }
}
