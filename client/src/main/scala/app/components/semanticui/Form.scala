package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedWithRawType}
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children, ReactEventFromInput}

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
              iconPosition: js.UndefOr[Position.Value] = js.undefined,
              placeholder: js.UndefOr[String] = js.undefined,
              typ: js.UndefOr[String] = js.undefined,
              label: js.UndefOr[String] = js.undefined,
              defaultValue: js.UndefOr[String] = js.undefined,
              error: js.UndefOr[Boolean] = js.undefined,
              onChange: (String) => Callback = _ => Callback.empty,
             )(children: VdomNode*): UnmountedWithRawType[js.Object, Null, RawMounted] = {
      val props = js.Dynamic.literal(
        fluid = fluid,
        icon = icon,
        iconPosition = iconPosition.map(Position.toStr),
        placeholder = placeholder,
        label = label,
        defaultValue = defaultValue,
        error = error,
        `type` = typ,
        onChange = (e: ReactEventFromInput) => onChange(e.target.value).runNow(),
      )
      component(props)(children:_*)
    }
  }

  object Group {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Form.Group)

    def apply(widths: js.UndefOr[Widths.Value] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        widths = widths.map(Widths.toStr),
      )
      component(props)(children:_*)
    }
  }

  object Field {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Form.Field)

    def apply(width: js.UndefOr[Width.Value] = js.undefined,
              error: js.UndefOr[Boolean] = js.undefined,
              inline: js.UndefOr[Boolean] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        width = width.map(Width.toStr),
        error = error,
        inline = inline,
      )
      component(props)(children:_*)
    }
  }

}