package app.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

object Dialog {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.Dialog)

  def apply(open: js.UndefOr[Boolean] = js.undefined,
            onRequestClose: Callback = Callback.empty
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      open = open,
      onRequestClose = onRequestClose.toJsCallback
    )
    component(props)(children:_*)
  }
}

