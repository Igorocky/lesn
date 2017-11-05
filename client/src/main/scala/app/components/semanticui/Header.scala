package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Header {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Header)

  def apply(as: js.UndefOr[String] = js.undefined,
            textAlign: js.UndefOr[TextAlign.Value] = js.undefined,
            color: js.UndefOr[Color.Value] = js.undefined,
            size: js.UndefOr[HeaderSize.Value] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      as = as,
      textAlign = textAlign.map(TextAlign.toStr),
      size = size.map(HeaderSize.toStr),
      color = color.map(Color.toStr),
    )
    component(props)(children:_*)
  }

}
