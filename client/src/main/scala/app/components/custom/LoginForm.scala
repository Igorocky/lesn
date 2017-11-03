package app.components.custom

import app.ClientUtils.whenDefined
import app.components.semanticui._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}

import scala.scalajs.js

trait LoginFormActions {
  def logIn(login: String, pass: String)(cb: Either[String, String] => Callback): Callback
}

object LoginForm {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: LoginFormActions) {
    @inline def render = comp(this)
  }

  implicit val stateReuse = Reusability.byRef[State]
  case class State(errorMsg: Option[String] = None, login: String = "", pass: String = "")

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .configure(Reusability.shouldComponentUpdate)
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = {
      <.div(^.className:="login-form",
        Grid(textAlign = TextAlign.Center, verticalAlign = VerticalAlign.Middle, style = js.Dynamic.literal(height = "100%"))(
          Grid.Column(style = js.Dynamic.literal(maxWidth = 450))(
            Header(as = "h2", color = Color.Teal, textAlign = TextAlign.Center)(
              "Log-in to your account"
            ),
            whenDefined(s.errorMsg){ msg=>
              Message(color = Color.Red)(
                Message.Header()(msg)
              )
            },
            Form(size = Size.Large)(
              Segment()(
                Form.Input(fluid = true, icon = "user", iconPosition = IconPosition.Left, placeholder = "Login",
                  onChange = v => $.modState(_.copy(login = v)))(),
                Form.Input(fluid = true, icon = "lock", iconPosition = IconPosition.Left, placeholder = "Password",
                  onChange = v => $.modState(_.copy(pass = v)), typ = "password")(),
                Button(primary = true, color = Color.Teal, fluid = true, size = ButtonSize.Large,
                  onClick = props.ctx.logIn(s.login, s.pass){
                    case Left(errMsg) => $.modState(_.copy(errorMsg = Some(errMsg)))
                  }
                )("Log-in")
              )
            )
          )
        )
      )
    }
  }
}