package app.components.forms

import app.components.semanticui.{Button, Color}
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, _}

object CancelButton {
  protected case class Props(text: String,
                             onClick: Callback
                            )

  protected case class State()

  def apply(text: String, onClick: Callback) =
    comp(Props(
      text = text
      ,onClick = onClick
    ))

  private lazy val comp = react.ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .build

  protected class Backend($: BackendScope[Props, State]) {

    def render(props: Props, state: State) = Button(
      primary = false, color = Color.Teal, onClick = props.onClick
    )(props.text)
  }
}
