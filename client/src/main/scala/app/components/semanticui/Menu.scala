package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.{Callback, Children}
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Menu {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Menu)

  def apply()(children: VdomNode*) = {
    val props = js.Dynamic.literal(
    )
    component(props)(children:_*)
  }

  object Item {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Menu.Item)

    def apply(name: js.UndefOr[String] = js.undefined,
              as: js.UndefOr[String] = js.undefined,
              active: js.UndefOr[Boolean] = js.undefined,
              onClick: Callback = Callback.empty,
              position: js.UndefOr[Position.Value] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        name = name,
        as = as,
        active = active,
        onClick = onClick.toJsCallback,
        position = position.map(Position.toStr),
      )
      component(props)(children:_*)
    }
  }

  object Menu {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Menu.Menu)

    def apply(position: js.UndefOr[Position.Value] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        position = position.map(Position.toStr),
      )
      component(props)(children:_*)
    }
  }

}
