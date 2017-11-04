package app.components.custom

import app.WsClient
import app.actions.GlobalContextImpl
import app.components.custom.userspage.UsersPageMem
import japgolly.scalajs.react.Callback
import shared.api.ServerApi
import shared.dto.User

case class ThePageState(modState: (ThePageState => ThePageState) => Callback = null,
                        getState: () => ThePageState = null,
                        serverApi: WsClient[ServerApi] = null,
                        windowFuncMem: WindowFuncMem = WindowFuncMem(),
                        routerMem: RouterMem = RouterMem(),
                        loggedInAs: Option[User] = None,
                        usersPageMem: UsersPageMem = UsersPageMem()) extends WindowFunc with GlobalContextImpl with Router {

  override protected def modWindowFuncMem(f: WindowFuncMem => WindowFuncMem): Callback =
    modState(s => s.copy(windowFuncMem = f(s.windowFuncMem)))

  override protected def modRouterMem(f: RouterMem => RouterMem): Callback =
    modState(s => s.copy(routerMem = f(s.routerMem)))

  override protected def modLoggedInAs(f: Option[User] => Option[User]) =
    modState(s => s.copy(loggedInAs = f(s.loggedInAs)))

  override protected def modUsersPageMem(f: UsersPageMem => UsersPageMem) =
    modState(s => s.copy(usersPageMem = f(s.usersPageMem)))

}
