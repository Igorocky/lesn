package app.components.custom.userspage

import app.ClientUtils
import app.Reusabilities._
import app.components.semanticui._
import japgolly.scalajs.react.extra.{Reusability, TimerSupport}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}
import shared.dto.User

import scala.concurrent.duration.DurationDouble
import scala.scalajs.js

trait UsersTableActions {
  def loadUsers: Callback
}

object UsersTable {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: UsersTableActions,
                   users: Option[List[User]]) {
    @inline def render = comp(this)
  }

  implicit val stateReuse = Reusability.byRef[State]
  case class State(loadDelayPassed: Boolean = false)

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .componentDidMount{$=>
      $.props.ctx.loadUsers >> $.backend.setInterval($.modState(_.copy(loadDelayPassed = true)), 1.second)
    }
    .configure(Reusability.shouldComponentUpdate)
    .configure(TimerSupport.install)
    .build

  class Backend($: BackendScope[Props, State]) extends TimerSupport {
    def render(implicit props: Props, s: State) = <.div(
      props.users match {
        case None => ClientUtils.when(s.loadDelayPassed) {
          Segment(style = js.Dynamic.literal(height = "100px"))(
            Dimmer(active = true)(
              Loader()("Loading users...")
            )
          )
        }
        case Some(users) =>
          Table(celled = true)(
            Table.Header()(
              Table.Row()(
                Table.HeaderCell()("Login"),
                Table.HeaderCell()("Role")
              )
            ),
            users match {
              case Nil => Table.Footer()(
                Table.Row()(
                  Table.HeaderCell(colSpan = 2)(
                    "No users"
                  )
                )
              )
              case users =>
                Table.Body()(
                  users.toVdomArray{user=>
                    Table.Row(key = user.id.get.toString)(
                      Table.Cell()(user.login),
                      Table.Cell()(user.role.toStr)
                    )
                  }
                )
            }
          )
      }
    )
  }
}