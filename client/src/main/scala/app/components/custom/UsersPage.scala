package app.components.custom

import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, ScalaComponent}

object UsersPage {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: WindowFunc) {
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
    def render(implicit props: Props, s: State) = <.div("Users")
  }
}