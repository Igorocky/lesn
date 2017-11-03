package app.components.custom

import app.WsClient
import japgolly.scalajs.react.Callback
import shared.api.ServerApi

case class ThePageState(modState: (ThePageState => ThePageState) => Callback = null,
                        getState: () => ThePageState = null,
                        windowFuncMem: WindowFuncMem = WindowFuncMem(),
                        globalMem: GlobalMem = GlobalMem(),
                        routerMem: RouterMem = RouterMem(),
                        serverApi: WsClient[ServerApi] = null) extends WindowFunc
  with GlobalContext with Router {

  override protected def modWindowFuncMem(f: WindowFuncMem => WindowFuncMem): Callback =
    modState(s => s.copy(windowFuncMem = f(s.windowFuncMem)))

  override protected def modGlobalMem(f: GlobalMem => GlobalMem): Callback =
    modState(s => s.copy(globalMem = f(s.globalMem)))

  override protected def modRouterMem(f: RouterMem => RouterMem): Callback =
    modState(s => s.copy(routerMem = f(s.routerMem)))


}
