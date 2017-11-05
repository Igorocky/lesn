package app.components.custom.userspage

import app.actions.GlobalContext
import app.components.custom.{NavigationBar, NavigationBarActions, Router, WindowFunc}
import app.components.semanticui._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}
import shared.dto.User

case class UsersPageMem(users: Option[List[User]] = None)

trait UsersPageActions {
  def userCreated(user: User): Callback
}

object UsersPage {
//  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: WindowFunc with GlobalContext with UsersTableActions with NavigationBarActions with Router with CreateUserFormActions with UsersPageActions) {
    @inline def render = comp(this)
  }

//  implicit val stateReuse = Reusability.byRef[State]
  case class State(newUserFormOpened: Boolean = false)

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
//    .configure(Reusability.shouldComponentUpdate)
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = Container()(
      NavigationBar.Props(props.ctx).render,
      Segment()(
        if (s.newUserFormOpened) {
          CreateUserForm.Props(
            ctx = props.ctx,
            cancelCreatingUser = props.ctx.confirmDialog(onConfirm = $.modState(_.copy(newUserFormOpened = false))),
            userCreated = newUser => props.ctx.userCreated(newUser) >> $.modState(_.copy(newUserFormOpened = false))
          ).render
        } else {
          Button(
            color = Color.Green,
//            style = js.Dynamic.literal(marginBottom = "15px"),
            onClick = $.modState(_.copy(newUserFormOpened = true))
          )("New user")
        }
      ),
      UsersTable.Props(props.ctx, props.ctx.usersPageMem.users).render
    )
  }
}