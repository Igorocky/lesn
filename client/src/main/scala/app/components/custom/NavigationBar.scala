package app.components.custom

import app.components.semanticui._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}

trait NavigationBarActions {
  def logout: Callback
}

object NavigationBar {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: NavigationBarActions with Router) {
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
    def render(implicit props: Props, s: State) =
      Menu()(
        Menu.Item(name = "users", as = "a", active = true)(
          "Users"
        ),
        Menu.Menu(position = Position.Right)(
          Menu.Item(name = "logout", as = "a")(
            "Logout"
          )
        )
      )
  }

}