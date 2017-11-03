package app.components.custom

import japgolly.scalajs.react.Callback

case class RouterMem(curPath: Path = DevHome)

trait Router {

  protected def modRouterMem(f: RouterMem => RouterMem): Callback

  private def mod(f: RouterMem => RouterMem): Callback = modRouterMem(f)

  def navigateToLoginPage = mod(_.copy(curPath = Login))
  def navigateToUsersPage = mod(_.copy(curPath = Users))
}
