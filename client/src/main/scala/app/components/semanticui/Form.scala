package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Form {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Form)

  def apply(as: js.UndefOr[String] = js.undefined,
            size: js.UndefOr[Size.Value] = js.undefined
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      as = as,
      size = size.map(Size.toStr)
    )
    component(props)(children:_*)
  }

  object Input {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Form.Input)

    def apply(fluid: js.UndefOr[Boolean] = js.undefined,
              icon: js.UndefOr[String] = js.undefined,
              iconPosition: js.UndefOr[IconPosition.Value] = js.undefined,
              placeholder: js.UndefOr[String] = js.undefined,
              typ: js.UndefOr[String] = js.undefined
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        fluid = fluid,
        icon = icon,
        iconPosition = iconPosition.map(IconPosition.toStr),
        placeholder = placeholder,
        `type` = typ
      )
      component(props)(children:_*)
    }
  }

}