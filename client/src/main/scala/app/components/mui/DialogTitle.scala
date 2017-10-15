package app.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object DialogTitle {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.DialogTitle)

  def apply(children: VdomNode*) = {
    val props = js.Dynamic.literal()
    component(props)(children:_*)
  }
}

