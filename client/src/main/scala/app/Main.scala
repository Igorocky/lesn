package app

import app.components.custom.ThePage
import org.scalajs.dom
import shared.utils.{SharedConstants, StrUtils}

import scala.scalajs.js

object Main extends js.JSApp {

  def main(): Unit = {
//    TapPlugin()
//    js.Dynamic.global.mui = Mui
//    js.Dynamic.global.mui.Styles = MuiStyles
//    js.Dynamic.global.mui.SvgIcons = MuiSvgIcons


//    router().renderIntoDOM(/*dom.document.body*/dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
//    LoginForm.Props().render.renderIntoDOM(dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))

    val customData: String = StrUtils.fromBytesStr(cropCdata(getValueFromDiv(SharedConstants.CUSTOM_DATA_DIV_ID)))
    println("customData = |>" + customData + "<|")
    ThePage(customData).renderIntoDOM(dom.document.getElementById(SharedConstants.UNIV_PAGE_CONTENT_DIV_ID))
  }

  private def getValueFromDiv(id: String) = dom.document.getElementById(id).innerHTML

  private def cropCdata(str: String) = str.substring(11, str.length - 5)
}

