package app.components.custom

import app.components.mui.AlignItemsValues._
import app.components.mui.ContentDirectionValues._
import app.components.mui.JustifyValues._
import app.components.mui._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}


object MuiLoginForm {
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
      Grid(container = true)(
        Grid(item = true, xs = 12)(
          Grid(container = true,
            direction = DIRECTION_COLUMN, justify = JUSTIFY_CENTER, alignItems = ALIGN_I_CENTER)(
            Grid(item = true, xs = 2)(
              TextField(
                value = Some(s.login),
                label = "Login",
                onChange = v => $.modState(_.copy(login = v))
              )
            ),
            Grid(item = true, xs = 2)(
              TextField(
                value = Some(s.pass),
                label = "Password",
                onChange = v => $.modState(_.copy(pass = v)),
                typ = InputTypes.PASSWORD
              )
            ),
            Grid(item = true, xs = 2)(
              Button(text = "Login", raised = true, onClick = Callback.info("Login"))
            )
          )
        )
      )
    }
  }
}