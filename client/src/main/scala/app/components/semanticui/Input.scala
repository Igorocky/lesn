package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children, ReactEventFromInput}
import org.scalajs.dom.html

import scala.scalajs.js

object Input {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Input)

  def apply(fluid: js.UndefOr[Boolean] = js.undefined,
            icon: js.UndefOr[String] = js.undefined,
            iconPosition: js.UndefOr[Position.Value] = js.undefined,
            placeholder: js.UndefOr[String] = js.undefined,
            typ: js.UndefOr[String] = js.undefined,
            label: Option[VdomNode] = None,
            defaultValue: js.UndefOr[String] = js.undefined,
            error: js.UndefOr[Boolean] = js.undefined,
            onChange: (String) => Callback = _ => Callback.empty,
            ref: js.UndefOr[html.Element => Unit ] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      fluid = fluid,
      icon = icon,
      iconPosition = iconPosition.map(Position.toStr),
      placeholder = placeholder,
      label = label.map(_.rawNode).getOrElse(js.undefined).asInstanceOf[js.Any],
      defaultValue = defaultValue,
      error = error,
      ref = ref,
      `type` = typ,
      onChange = (e: ReactEventFromInput) => onChange(e.target.value).runNow(),
    )
    component(props)(children: _*)
  }
}