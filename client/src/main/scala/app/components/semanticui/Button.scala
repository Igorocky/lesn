package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

object Button {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Button)

  def apply(key: js.UndefOr[String] = js.undefined,
            onClick: Callback = Callback.empty,
            primary: js.UndefOr[Boolean] = js.undefined,
            fluid: js.UndefOr[Boolean] = js.undefined,
            size: js.UndefOr[ButtonSize.Value] = js.undefined,
            color: js.UndefOr[Color.Value] = js.undefined
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      key = key,
      onClick = onClick.toJsCallback,
      primary = primary,
      fluid = fluid,
      size = size.map(ButtonSize.toStr),
      color = color.map(Color.toStr),
    )
    component(props)(children:_*)
  }
}

