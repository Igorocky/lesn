package app.components

import app.components.semanticui._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}

import scala.scalajs.js


object SemanticUiLoginForm {
  case class Props() {
    @inline def render = comp(this)
  }
  case class State(login: String = "", pass: String = "")

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = {
      <.div(^.className:="login-form",
        Grid(textAlign = TextAlign.Center, verticalAlign = VerticalAlign.Middle, style = js.Dynamic.literal(height = "100%"))(
          Grid.Column(style = js.Dynamic.literal(maxWidth = 450))(
            Header(as = "h2", color = Color.Teal, textAlign = TextAlign.Center)(
              "Log-in to your account"
            ),
            Form(size = Size.Large)(
              Segment()(
                Form.Input(fluid = true, icon = "user", iconPosition = IconPosition.Left, placeholder = "Login")(),
                Form.Input(fluid = true, icon = "lock", iconPosition = IconPosition.Left, placeholder = "Password", typ = "password")(),
                Button(primary = true, onClick = Callback.info("ON_CLICK!!!"), color = Color.Teal, fluid = true, size = ButtonSize.Large)(
                  "Log-in"
                )
              )
            )
          )
        )
      )
    }
  }
}