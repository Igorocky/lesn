package app.components.custom

import japgolly.scalajs.react.Callback

case class WindowFuncMem(waitPane: Boolean = false,
                         okDiagText: Option[String] = None,
                         okCancelDiagText: Option[String] = None,
                         onOk: Callback = Callback.empty,
                         onCancel: Callback = Callback.empty)

trait WindowFunc {
  protected def modWindowFuncMem(f: WindowFuncMem => WindowFuncMem): Callback

  private def mod(f: WindowFuncMem => WindowFuncMem): Callback = modWindowFuncMem(f)

  def openWaitPane: Callback = mod(_.copy(waitPane = true))

  def closeWaitPane: Callback = mod(_.copy(waitPane = false))

  def openOkDialog(text: String): Callback = mod(_.copy(okDiagText = Some(text)))

  def closeOkDialog: Callback = mod(_.copy(okDiagText = None))

  def openOkCancelDialog(text: String, onOk: Callback, onCancel: Callback): Callback =
    mod(_.copy(okCancelDiagText = Some(text), onOk = onOk, onCancel = onCancel))

  def closeOkCancelDialog: Callback =
    mod(_.copy(okCancelDiagText = None, onOk = Callback.empty, onCancel = Callback.empty))

  def showError(throwable: Throwable): Callback = openOkDialog("Error: " + throwable)
}