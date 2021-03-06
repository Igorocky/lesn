package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Dimmer {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Dimmer)

  def apply(active: js.UndefOr[Boolean] = js.undefined,
            inverted: js.UndefOr[Boolean] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      active = active,
      inverted = inverted,
    )
    component(props)(children:_*)
  }
}

