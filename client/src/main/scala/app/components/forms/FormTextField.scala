package app.components.forms

import app.components.semanticui.{Form, Popup, PopupPosition}
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, _}
import org.scalajs.dom.html
import shared.forms.FormField

object FormTextField {
  protected case class Props(focusOnMount: Boolean,
                             value: String,
                             errors: List[String],
                             onChange: String => CallbackTo[_],
                             editMode: Boolean,
                             onEnter: Callback,
                             placeholder: String,
                             onEscape: Callback)

  protected case class State()

  def apply[T, S](field: FormField[T, String], focusOnMount: Boolean = false, onEscape: Callback = Callback.empty)
                 (implicit formParams: FormCommonParams[T, S]) =
    comp(Props(
      focusOnMount = focusOnMount
      ,value = field.get(formParams.formData)
      ,errors = field.errors(formParams.formData)
      ,onChange = formParams.valueWasChanged(field)
      ,editMode = formParams.editMode
      ,onEnter = formParams.submit
      ,placeholder = field.label
      ,onEscape = onEscape
    ))

  private lazy val comp = react.ScalaComponent.builder[Props](this.getClass.getName)
    .initialStateFromProps(p => State())
    .renderBackend[Backend]
    .componentDidMount{$ => if ($.props.focusOnMount) Callback($.backend.theInput.focus()) else Callback.empty}
    .build

  protected class Backend($: BackendScope[Props, State]) {
    var theInput: html.Element = _

    def render(props: Props, state: State) = {
      val field = Form.Field(error = props.errors.nonEmpty)(
        <.label(props.placeholder),
        <.input.text.ref(theInput = _)(
          ^.placeholder:=props.placeholder,
          ^.value := props.value,
          ^.onChange ==> { e: ReactEventFromInput => props.onChange(e.target.value).void },
          ^.onKeyDown ==> { (e: ReactKeyboardEvent) =>
            if (e.keyCode == 13) {
              props.onEnter
            } else if (e.keyCode == 27) {
              props.onEscape
            } else {
              Callback.empty
            }
          }
        )
      )
      if (props.errors.nonEmpty) {
        Popup(
          trigger = Some(field),
          content = props.errors.mkString("; "),
          position = PopupPosition.BottomLeft
        )()
      } else {
        field
      }
    }

  }

}
