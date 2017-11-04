package app.components.custom.userspage

import app.actions.GlobalContext
import app.components.custom.WindowFunc
import app.components.forms.FormCommonParams.SubmitFunction
import app.components.forms.{FormCommonParams, FormErrors, FormTextField}
import app.components.semanticui._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}
import shared.dto.{User, UserFull}
import shared.forms.{FormData, Forms}
import app.Reusabilities._
import shared.messages.Languages

trait CreateUserFormActions {
  def createUser: SubmitFunction[UserFull,User]
}

object CreateUserForm {
  implicit val propsReuse = Reusability.caseClassExcept[Props]('ctx)
  case class Props(ctx: CreateUserFormActions with WindowFunc with GlobalContext,
                   cancelCreatingUser: Callback,
                   userCreated: User => Callback) {
    @inline def render = comp(this)
  }

  implicit val stateReuse = Reusability.byRef[State]
  case class State(formData: FormData[UserFull])

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialStateFromProps(props => State(formData = FormData(Languages.EN, UserFull())))
    .renderBackend[Backend]
    .configure(Reusability.shouldComponentUpdate)
    .build

  class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = {
      val formMethods = Forms.createUserForm
      implicit val fParams = FormCommonParams[UserFull, User](
        formData = s.formData,
        formMethods = formMethods,
        onChange = fd => $.modState(_.copy(formData = fd)).map(_ => fd),
        beforeSubmit = props.ctx.openWaitPane,
        submitFunction = props.ctx.createUser,
        onSubmitSuccess = newUser => props.userCreated(newUser),
        onSubmitFormCheckFailure = props.ctx.closeWaitPane,
        editMode = false
      )
      Form()(
        FormErrors(),
        Form.Group()(
          FormTextField(
            field = formMethods.login,
            focusOnMount = true,
            onEscape = props.cancelCreatingUser
          )
        )
      )
    }
  }
}