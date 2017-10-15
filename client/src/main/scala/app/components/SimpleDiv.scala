package app.components

import app.components.mui.{Button, CmpBuilder, InputTypes, TextField}
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}


object SimpleDiv {
  case class Props(text: String) {
    @inline def render = comp(this)
  }
  case class State(isOpen: Boolean, login: Option[String] = None, pass: Option[String] = None)

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State(false))
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    val open = $.modState(_.copy(isOpen = true))
    val close = $.modState(_.copy(isOpen = false))

    def handleDialogCancel: Callback = close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit: react.ReactEvent => Callback =
      e => close >> Callback.info("Submit Clicked")

    val openDialog: Callback = open >> Callback.info("Opened")

    def render(s: State) = {
      <.div(
        TextField(
          value = s.login,
          label = "Login",
          onChange = v => $.modState(_.copy(login = Some(v)))
        ),
        TextField(
          value = s.pass,
          label = "Password",
          onChange = v => $.modState(_.copy(pass = Some(v))),
          typ = InputTypes.PASSWORD
        ),
        VdomNode.cast(s.login.getOrElse("No Login")),
        Button(text = "Open", raised = true, onClick = openDialog),
        if (s.isOpen) {
          CmpBuilder.okDialog("Title", "Some Text", handleDialogCancel)
        } else {
          EmptyVdom
        }
      )
//      val actions: VdomNode = js.Array(
//        MuiFlatButton(key = "1", label = "Cancel", secondary = false, onTouchTap = handleDialogCancel)(),
//        MuiFlatButton(key = "2", label = "Submit", secondary = true, onTouchTap = handleDialogSubmit)()
//      ).toVdomArray

//      MuiMuiThemeProvider()(
//        <.div(
//          <.div(
//            MuiDialog(
//              title = "Dialog With Actions",
//              actions = actions,
//              open = S.isOpen,
//              //              onRequestClose = Callback(println("onRequestClose"))
//            )(
//              "Dialog example with floating buttons"
//            ),
//            MuiRaisedButton(key = "k1", label = "Open", primary = true, onTouchTap = openDialog)()
//          )
//        )
//      )
    }
  }
}