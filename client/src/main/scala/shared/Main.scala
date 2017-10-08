package shared

import chandu0101.scalajs.react.components.materialui.MuiRaisedButton
import org.scalajs.dom
import shared.components.SimpleDiv

import scala.scalajs.js
import scalacss.internal.mutable.GlobalRegistry

object Main extends js.JSApp {

  def main(): Unit = {
    TapPlugin()
    js.Dynamic.global.mui = Mui
    js.Dynamic.global.mui.Styles = MuiStyles
    js.Dynamic.global.mui.SvgIcons = MuiSvgIcons
//    GlobalRegistry.register(MuiRaisedButton.Style)

    SimpleDiv.Props("SimpleDiv").render.renderIntoDOM(dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
  }
}