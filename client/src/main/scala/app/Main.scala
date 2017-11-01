package app

import app.components.{MuiLoginForm, SemanticUiLoginForm}
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom
import shared.SharedConstants
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js

object Main extends js.JSApp {

  def main(): Unit = {
//    TapPlugin()
//    js.Dynamic.global.mui = Mui
//    js.Dynamic.global.mui.Styles = MuiStyles
//    js.Dynamic.global.mui.SvgIcons = MuiSvgIcons

    val routerConfig = RouterConfigDsl[MyPages].buildConfig { dsl =>
      import dsl._

      (emptyRule
        | staticRoute(root, Root)  ~> redirectToPage(SemanticUiLoginPage)(Redirect.Replace)
//        | staticRoute(root, Root)  ~> render(<.div("Root"))
        | staticRoute("#mui-login", MuiLoginPage) ~> render(MuiLoginForm.Props().render)
        | staticRoute("#semantic-ui-login", SemanticUiLoginPage) ~> render(SemanticUiLoginForm.Props().render)
        | staticRoute("#pageNotFound", PageNotFound) ~> render(<.div("PageNotFound"))
        ).notFound(redirectToPage(Root)(Redirect.Replace))
    }

    val router = Router(BaseUrl.fromWindowOrigin, routerConfig)
    router().renderIntoDOM(/*dom.document.body*/dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
//    LoginForm.Props().render.renderIntoDOM(dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
  }
}

sealed trait MyPages
case object MuiLoginPage extends MyPages
case object SemanticUiLoginPage extends MyPages
case object Root extends MyPages
case object PageNotFound extends MyPages