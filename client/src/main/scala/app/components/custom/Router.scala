package app.components.custom

import japgolly.scalajs.react.Callback

case class RouterMem(curPath: Path = DevHome)

trait Router {

  protected def modRouterMem(f: RouterMem => RouterMem): Callback

  def navigateToLoginPage = modRouterMem(_.copy(curPath = Login))
  def navigateToUsersPage = modRouterMem(_.copy(curPath = Users))
}
