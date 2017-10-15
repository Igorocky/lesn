package shared.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

object TextField {
  trait Props extends js.Object {
    var label: js.UndefOr[String] = js.undefined
    var color: js.UndefOr[String] = js.undefined
    var onClick: js.UndefOr[js.Function0[Unit]] = js.undefined
  }
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.Button)

  def apply(label: js.UndefOr[String] = js.undefined,
            raised: js.UndefOr[Boolean] = js.undefined,
            color: js.UndefOr[String] = js.undefined,
            onClick: Callback = Callback.empty
           ) = {
//    val props = new Props{}
    val props = js.Dynamic.literal(
      label = label
    )
    props.raised = raised
    props.color = color
    props.onClick = onClick.toJsCallback

    component(props)("")
  }
}

