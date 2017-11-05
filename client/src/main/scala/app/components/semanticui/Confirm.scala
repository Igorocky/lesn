package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js
import japgolly.scalajs.react.vdom.html_<^._

object Confirm {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Confirm)

  def apply(content: Option[VdomNode] = None,
            cancelButton: Option[VdomNode] = Some("Cancel"),
            confirmButton: Option[VdomNode] = Some("OK"),
            onCancel: Callback = Callback.empty,
            onConfirm: Callback = Callback.empty,
            open: js.UndefOr[Boolean] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      content = content.map(_.rawNode).getOrElse(js.undefined).asInstanceOf[js.Any],
      cancelButton = cancelButton.map(_.rawNode).getOrElse(js.undefined).asInstanceOf[js.Any],
      confirmButton = confirmButton.map(_.rawNode).getOrElse(js.undefined).asInstanceOf[js.Any],
      onCancel = onCancel.toJsCallback,
      onConfirm = onConfirm.toJsCallback,
      open = open,
    )
    component(props)(children:_*)
  }
}

