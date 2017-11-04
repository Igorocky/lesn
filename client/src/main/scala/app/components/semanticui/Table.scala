package app.components.semanticui

import japgolly.scalajs.react
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

object Table {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table)

  def apply(celled: js.UndefOr[Boolean] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal(
      celled = celled
    )
    component(props)(children:_*)
  }

  object Header {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.Header)

    def apply()(children: VdomNode*) = {
      val props = js.Dynamic.literal(
      )
      component(props)(children:_*)
    }
  }

  object Row {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.Row)

    def apply(key: js.UndefOr[Any] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        key = key.asInstanceOf[js.Any],
      )
      component(props)(children:_*)
    }
  }

  object HeaderCell {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.HeaderCell)

    def apply(colSpan: js.UndefOr[Int] = js.undefined,
             )(children: VdomNode*) = {
      val props = js.Dynamic.literal(
        colSpan = colSpan
      )
      component(props)(children:_*)
    }
  }

  object Body {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.Body)

    def apply()(children: VdomNode*) = {
      val props = js.Dynamic.literal(
      )
      component(props)(children:_*)
    }
  }

  object Cell {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.Cell)

    def apply()(children: VdomNode*) = {
      val props = js.Dynamic.literal(
      )
      component(props)(children:_*)
    }
  }

  object Footer {
    val component = react.JsComponent[js.Object, Children.Varargs, Null](SemanticUiComponents.Table.Footer)

    def apply()(children: VdomNode*) = {
      val props = js.Dynamic.literal(
      )
      component(props)(children:_*)
    }
  }

}
