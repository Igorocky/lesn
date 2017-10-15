package app

import app.components.SimpleDiv
import org.scalajs.dom
import shared.SharedConstants

import scala.scalajs.js

object Main extends js.JSApp {

  def main(): Unit = {
//    TapPlugin()
//    js.Dynamic.global.mui = Mui
//    js.Dynamic.global.mui.Styles = MuiStyles
//    js.Dynamic.global.mui.SvgIcons = MuiSvgIcons

    SimpleDiv.Props("SimpleDiv").render.renderIntoDOM(dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
  }
}