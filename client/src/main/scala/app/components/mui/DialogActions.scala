package app.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object DialogActions {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.DialogActions)
  def apply(children: VdomNode*) = {
    val props = js.Dynamic.literal()
    component(props)(children:_*)
  }
}

