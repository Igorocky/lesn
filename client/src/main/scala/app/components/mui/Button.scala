package app.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

object Button {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.Button)

  def apply(text: String,
            raised: js.UndefOr[Boolean] = js.undefined,
            color: js.UndefOr[MuiColor] = js.undefined,
            onClick: Callback = Callback.empty
           ) = {
    val props = js.Dynamic.literal(
      raised = raised,
      color = color.map(_.strValue),
      onClick = onClick.toJsCallback
    )
    component(props)(text)
  }
}

