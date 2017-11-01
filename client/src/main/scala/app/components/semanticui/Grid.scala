package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Grid {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Grid)

  def apply(textAlign: js.UndefOr[TextAlign.Value] = js.undefined,
            verticalAlign: js.UndefOr[VerticalAlign.Value] = js.undefined,
            style: js.UndefOr[js.Object] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      textAlign = textAlign.map(TextAlign.toStr),
      verticalAlign = verticalAlign.map(VerticalAlign.toStr),
      style = style
    )
    component(props)(children:_*)
  }

  object Column {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Grid.Column)

    def apply(style: js.UndefOr[js.Object] = js.undefined)(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        style = style
      )
      component(props)(children:_*)
    }
  }

}
