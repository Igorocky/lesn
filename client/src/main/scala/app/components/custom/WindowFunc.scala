package app.components.custom

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.html_<^._

case class ConfirmDialogParams(content: String,
                               cancelButton: Option[VdomNode],
                               confirmButton: Option[VdomNode],
                               onCancel: Callback,
                               onConfirm: Callback
                              )

case class WindowFuncMem(confirmDialogParams: Option[ConfirmDialogParams] = None,
                         waitText: Option[String] = None
                        )

trait WindowFunc {
  protected def modWindowFuncMem(f: WindowFuncMem => WindowFuncMem): Callback

  private def mod(f: WindowFuncMem => WindowFuncMem): Callback = modWindowFuncMem(f)

  def confirmDialog(content: String = "Are you sure?",
                    cancelButton: Option[VdomNode] = Some("Cancel"),
                    confirmButton: Option[VdomNode] = Some("OK"),
                    onCancel: Callback = Callback.empty,
                    onConfirm: Callback = Callback.empty) = mod(_.copy(confirmDialogParams = Some(ConfirmDialogParams(
    content = content,
    cancelButton = cancelButton,
    confirmButton = confirmButton,
    onCancel = onCancel >> mod(_.copy(confirmDialogParams = None)),
    onConfirm = onConfirm >> mod(_.copy(confirmDialogParams = None))
  ))))

  def showError(throwable: Throwable): Callback = showError(throwable, Callback.empty)

  def showError(throwable: Throwable, onOk: Callback): Callback = confirmDialog(
    content = "Error: " + throwable,
    onConfirm = onOk,
    cancelButton = Some(<.span())
  )

  def openWaitPane(text: String = ""): Callback = mod(_.copy(waitText = Some(text)))

  def closeWaitPane: Callback = mod(_.copy(waitText = None))
}