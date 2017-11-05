package app.components.forms

import app.components.semanticui.Form
import japgolly.scalajs.react
import japgolly.scalajs.react.{Callback, _}
import org.scalajs.dom.html
import shared.forms.FormField
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenTraversableOnce

object FormSelect {
  protected case class Props(value: String,
                             errors: List[String],
                             onChange: String => Callback,
                             placeholder: String,
                             options: js.Object)

  protected case class State()

  def apply[T, F, S](field: FormField[T, F], values: Map[F, String])
                 (implicit formParams: FormCommonParams[T, S]) =
    comp(Props(
      value = values(field.get(formParams.formData))
      ,errors = field.errors(formParams.formData)
      ,onChange = str => formParams.valueWasChanged(field)(values.find(_._2 == str).get._1).void
      ,placeholder = field.label
      ,options = values.map { case (_, v) =>
        js.Dynamic.literal(
          key = v, text = v, value = v
        )
      }.toJSArray
    ))

  private lazy val comp = react.ScalaComponent.builder[Props](this.getClass.getName)
    .initialStateFromProps(p => State())
    .renderBackend[Backend]
    .build

  protected class Backend($: BackendScope[Props, State]) {
    var theInput: html.Element = _

    def render(props: Props, state: State) = Form.Select(
      placeholder = props.placeholder,
      label = props.placeholder,
      onChange = props.onChange,
      value = props.value,
      options = props.options
    )()

  }
}
