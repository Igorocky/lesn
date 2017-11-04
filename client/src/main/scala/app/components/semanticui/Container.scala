package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Container {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Container)

  def apply()(children: VdomNode*) = {
    val props = js.Dynamic.literal(
    )
    component(props)(children:_*)
  }
}

