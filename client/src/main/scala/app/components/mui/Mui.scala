package app.components.mui

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("material-ui", JSImport.Namespace)
object MuiComponents extends js.Object {
  val Button: js.Dynamic = js.native

  val Dialog: js.Dynamic = js.native
  val DialogActions: js.Dynamic = js.native
  val DialogContent: js.Dynamic = js.native
  val DialogContentText: js.Dynamic = js.native
  val DialogTitle: js.Dynamic = js.native
  val TextField: js.Dynamic = js.native
  val Grid: js.Dynamic = js.native
}

case class MuiColor(strValue: String)
object MuiColors {
  final val DEFAULT = MuiColor("default")
  final val INHERIT = MuiColor("inherit")
  final val PRIMARY = MuiColor("primary")
  final val ACCENT = MuiColor("accent")
  final val CONTRAST = MuiColor("contrast")
}

case class InputType(strValue: String)
object InputTypes {
  final val PASSWORD = InputType("password")
}