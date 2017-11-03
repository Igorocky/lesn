package app.components.custom

import app._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, ScalaComponent}
import shared.api.ThePageParams
import upickle.default.read


object ThePage {
  protected type Props = ThePageParams
  protected type State = ThePageState

  def apply(str: String) = comp(read[Props](str))

  private lazy val comp = ScalaComponent.builder[Props](this.getClass.getName)
    .initialStateFromProps(p => ThePageState())
    .renderBackend[Backend]
    .componentWillMount { $ =>
      $.modState {_.copy(
        modState = $.modState(_),
        getState = () => $.state,
        serverApi = ClientUtils.createWsClient($.props.wsEntryUrl)
      )}
    }.build

  class Backend($: BackendScope[Props, State]) {

    def render(implicit props: Props, s: State) = <.div(s.routerMem.curPath match {
      case DevHome => DevHomePage.Props(s).render
      case Login => LoginForm.Props(s).render
      case Users => UsersPage.Props(s).render
    })
  }
}