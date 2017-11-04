package app.actions

import app.WsClient
import app.components.custom.WindowFunc
import app.components.custom.userspage.UsersPageMem
import japgolly.scalajs.react.Callback
import shared.api.ServerApi
import shared.dto._

trait GlobalContext {
  def serverApi: WsClient[ServerApi]
  def loggedInAs: Option[User]
  def usersPageMem: UsersPageMem

  protected def modLoggedInAs(f: Option[User] => Option[User]): Callback
  protected def modUsersPageMem(f: UsersPageMem => UsersPageMem): Callback
}

trait GlobalContextImpl extends GlobalContext
  with WindowFunc
  with LoginFormActionsImpl
  with NavigationBarActionsImpl
  with UsersPageActionsImpl
  with CreateUserFormActionsImpl
  with UsersTableActionsImpl
