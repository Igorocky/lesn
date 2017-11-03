package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Loader {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Loader)

  def apply(active: js.UndefOr[Boolean] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      active = active,
    )
    component(props)(children:_*)
  }
}

