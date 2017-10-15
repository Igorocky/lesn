package shared.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.{VdomElement, VdomNode}
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

object Dialog {
  trait Props extends js.Object {
    var open: js.UndefOr[Boolean] = js.undefined
    var onRequestClose: js.UndefOr[js.Function0[Unit]] = js.undefined
  }
  val component = react.JsComponent[Props, Children.Varargs, Null](MuiComponents.Dialog)

  def apply(open: js.UndefOr[Boolean] = js.undefined,
            onRequestClose: Callback = Callback.empty
           )(children: VdomNode*) = {
    val props = new Props{}
    props.open = open
    props.onRequestClose = onRequestClose.toJsCallback

    component(props)(children:_*)
  }
}

