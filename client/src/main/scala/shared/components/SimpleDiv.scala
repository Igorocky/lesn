package shared.components

import chandu0101.scalajs.react.components.Implicits._
import chandu0101.scalajs.react.components.materialui.{MuiDialog, MuiFlatButton, MuiIconButton, MuiMuiThemeProvider, MuiRaisedButton}
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ScalaComponent}

import scala.scalajs.js


object SimpleDiv {
  case class Props(text: String) {
    @inline def render = comp(this)
  }
  case class State(isOpen: Boolean)

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State(false))
    .renderBackend[Backend]
    .build

  class Backend($: BackendScope[Props, State]) {
    val open = $.setState(State(true))
    val close = $.setState(State(false))

    def handleDialogCancel: react.ReactEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit: react.ReactEvent => Callback =
      e => close >> Callback.info("Submit Clicked")

    val openDialog: react.ReactEvent => Callback =
      e => open >> Callback.info("Opened")

    def render(S: State) = {
      val actions: VdomNode = js.Array(
        MuiFlatButton(key = "1", label = "Cancel", secondary = false, onTouchTap = handleDialogCancel)(),
        MuiFlatButton(key = "2", label = "Submit", secondary = true, onTouchTap = handleDialogSubmit)()
      ).toVdomArray

      MuiMuiThemeProvider()(
        <.div(
          <.div(
            MuiDialog(
              title = "Dialog With Actions",
              actions = actions,
              open = S.isOpen,
              //              onRequestClose = Callback(println("onRequestClose"))
            )(
              "Dialog example with floating buttons"
            ),
            MuiRaisedButton(key = "k1", label = "Open", primary = true, onTouchTap = openDialog)()
          )
        )
      )
    }
  }
}