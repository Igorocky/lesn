package shared.components.mui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object DialogContent {
  trait Props extends js.Object {
  }
  val component = react.JsComponent[Props, Children.Varargs, Null](MuiComponents.DialogContent)

  def apply(children: VdomNode*) = {
    val props = new Props{}

    component(props)(children:_*)
  }
}

