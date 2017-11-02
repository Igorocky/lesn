package app.components.custom

import app._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.extra.router.{BaseUrl, Redirect, Router, RouterConfigDsl}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, ScalaComponent}
import shared.api.ThePageParams
import upickle.default.read


object ThePage {
  protected type Props = ThePageParams
  protected type State = ThePageState

  def apply(str: String): Unmounted[Props, State, Backend] = comp(read[Props](str))

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

    private val routerConfig = RouterConfigDsl[MyPages].buildConfig { dsl =>
      import dsl._

      (emptyRule
        | staticRoute(root, Root)  ~> redirectToPage(LoginPage)(Redirect.Replace)
        | staticRoute("#login", LoginPage) ~> dsl.render(LoginForm.Props($.state.runNow()).render)
        | staticRoute("#mui-login", MuiLoginPage) ~> dsl.render(MuiLoginForm.Props().render)
        | staticRoute("#pageNotFound", PageNotFound) ~> dsl.render(<.div("PageNotFound"))
        ).notFound(redirectToPage(Root)(Redirect.Replace))
    }

    private val router = Router(BaseUrl.fromWindowOrigin, routerConfig)

    def render(implicit props: Props, s: State) = router()
  }
}