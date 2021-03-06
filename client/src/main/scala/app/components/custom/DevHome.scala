package app.components.custom

import app.components.semanticui._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}

object DevHomePage {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: Router) {
    @inline def render = comp(this)
  }

  implicit val stateReuse = Reusability.byRef[State]
  case class State()

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .configure(Reusability.shouldComponentUpdate)
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = {
      val pages: Map[String, Callback] = Map(
        "Log-in" -> props.ctx.navigateToLoginPage
        ,"Users" -> props.ctx.navigateToUsersPage
      )
      <.div(pages.toVdomArray{case (str, cb)=>
        Button(key = str, primary = true, color = Color.Teal, size = ButtonSize.Large, onClick = cb)(str)
      })
//        ,
//        Button(primary = true, color = Color.Teal, size = ButtonSize.Large, onClick = props.ctx.navigateToUsersPage)(
//          "Users"
//        )
//      )
    }
  }
}