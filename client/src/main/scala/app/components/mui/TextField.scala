package app.components.mui

import app.ClientUtils.mergeJSObjects
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, Children, ReactEventFromInput}

import scala.scalajs.js

object TextField {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.TextField)

  def apply(label: js.UndefOr[String] = js.undefined,
            onChange: (String) => Callback,
            value: Option[String] = None,
            typ: js.UndefOr[InputType] = js.undefined
           ) = {
    val props = mergeJSObjects(js.Dynamic.literal(
      label = label,
      onChange = (e: ReactEventFromInput) => onChange(e.target.value).runNow(),
      value = value.getOrElse(js.undefined).asInstanceOf[js.UndefOr[String]]
    ), js.Dynamic.literal(
      "type" -> typ.map(_.strValue)
    ))
    component(props)()
  }
}

