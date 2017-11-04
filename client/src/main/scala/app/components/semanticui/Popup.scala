package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Popup {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Popup)

  def apply(basic: js.UndefOr[Boolean] = js.undefined,
            trigger: Option[VdomNode] = None,
            content: js.UndefOr[String] = js.undefined,
            position: js.UndefOr[PopupPosition.Value] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      basic = basic,
      content = content,
      position = position.map(PopupPosition.toStr),
      trigger = trigger.map(_.rawNode).getOrElse(js.undefined).asInstanceOf[js.Any],
    )
    component(props)(children: _*)
  }
}