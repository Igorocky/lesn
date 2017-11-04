package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Label {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Label)

  def apply(basic: js.UndefOr[Boolean] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      basic = basic
    )
    component(props)(children: _*)
  }
}