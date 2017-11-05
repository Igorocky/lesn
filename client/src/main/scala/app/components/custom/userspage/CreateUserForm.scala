package app.components.custom.userspage

import app.actions.GlobalContext
import app.components.custom.WindowFunc
import app.components.forms.FormCommonParams.SubmitFunction
import app.components.forms._
import app.components.semanticui._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}
import shared.dto.{CreateUserRequest, User, UserRole}
import shared.forms.{FormData, Forms}
import app.Reusabilities._
import shared.messages.{Languages}

import scala.scalajs.js

trait CreateUserFormActions {
  def createUser: SubmitFunction[CreateUserRequest,User]
}

object CreateUserForm {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: CreateUserFormActions with WindowFunc with GlobalContext,
                   cancelCreatingUser: Callback,
                   userCreated: User => Callback) {
    @inline def render = comp(this)
  }

  implicit val stateReuse = Reusability.byRef[State]
  case class State(formData: FormData[CreateUserRequest])

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialStateFromProps(props => State(formData = FormData(Languages.EN, CreateUserRequest())))
    .renderBackend[Backend]
    .configure(Reusability.shouldComponentUpdate)
    .build

  private val roles = Map(
    UserRole.Admin -> "Admin",
    UserRole.Teacher -> "Teacher",
    UserRole.Pupil -> "Pupil"
  )

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = {
      val formMethods = Forms.createUserForm
      implicit val fParams = FormCommonParams[CreateUserRequest, User](
        formMethods = formMethods,
        formData = s.formData,
        onChange = fd => $.modState(_.copy(formData = fd)).map(_ => fd),
        submitFunction = props.ctx.createUser,
        onSubmitSuccess = newUser => props.userCreated(newUser),
        windowFunc = props.ctx
      )

      Form()(
        Header(size = HeaderSize.Medium)("Create new user"),
        FormErrors(),
        Form.Group()(
          FormTextField(field = formMethods.login, focusOnMount = true, onEscape = props.cancelCreatingUser),
          FormSelect(field = formMethods.role, values = roles),
          FormTextField(field = formMethods.password, onEscape = props.cancelCreatingUser, password = true),
          FormTextField(field = formMethods.repeatPassword, onEscape = props.cancelCreatingUser, password = true)
        ),
        Form.Group()(
          SubmitButton("Create user"),
          CancelButton("Cancel", props.cancelCreatingUser)
        )
      )
    }
  }
}