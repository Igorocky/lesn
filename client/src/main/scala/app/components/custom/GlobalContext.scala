package app.components.custom

import app.WsClient
import app.components.custom.userspage.{UsersPageActions, UsersPageMem, UsersTableActions}
import japgolly.scalajs.react.Callback
import shared.api.ServerApi
import shared.dto._

case class GlobalMem(
                      loggedInAs: Option[User] = None,
                      usersPageMem: UsersPageMem = UsersPageMem(),
                    )

trait GlobalContext extends LoginFormActions with WindowFunc with UsersPageActions with UsersTableActions {

  //abstract members
  val serverApi: WsClient[ServerApi]

  protected def modGlobalMem(f: GlobalMem => GlobalMem): Callback

  private def mod(f: GlobalMem => GlobalMem): Callback = modGlobalMem(f)

  protected def globalMem: GlobalMem

  protected def modUsersPageMem(f: UsersPageMem => UsersPageMem) = modGlobalMem(_.copy(usersPageMem = f(globalMem.usersPageMem)))

  override def usersPageMem = globalMem.usersPageMem

  //actions
  override def logIn(login: String, pass: String)(cb: Either[String, User] => Callback) =
    serverApi.post(_.logIn(login, pass), showError){
      case res @ Right(loggedInAs) => mod(_.copy(loggedInAs = Some(loggedInAs))) >> cb(res)
      case res => mod(_.copy(loggedInAs = None)) >> cb(res)
    }

  override def loadUsers = serverApi.post(_.listUsers(), showError){
    case Right(users) => modUsersPageMem(_.copy(users = Some(users)))
    case _ => modUsersPageMem(_.copy(users = Some(Nil)))
  }
}
