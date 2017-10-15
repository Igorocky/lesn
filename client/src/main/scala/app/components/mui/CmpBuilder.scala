package app.components.mui

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.html_<^._

object CmpBuilder {
  def okDialog(title: String, text: String, onOk: Callback = Callback.empty) = {
    Dialog(open = true)(
      DialogTitle(title),
      DialogContent(
        DialogContentText(text)
      ),
      DialogActions(
        Button(text = "OK", raised = false, onClick = onOk)
      )
    )
  }

}
