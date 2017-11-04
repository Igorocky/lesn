package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Message {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Message)

  def apply(color: js.UndefOr[Color.Value] = js.undefined,
            icon: js.UndefOr[String] = js.undefined,
            key: js.UndefOr[Any] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      color = color.map(Color.toStr),
      icon = icon,
      key = key.asInstanceOf[js.Any],
    )
    component(props)(children:_*)
  }

  object Header {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Message.Header)

    def apply()(children: VdomNode*) = {
      val props = js.Dynamic.literal(
      )
      component(props)(children:_*)
    }
  }

}
