package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Segment {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Segment)

  def apply(stacked: js.UndefOr[Boolean] = js.undefined,
            style: js.UndefOr[js.Object] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      stacked = stacked,
      style = style
    )
    component(props)(children:_*)
  }
}