package shared.components.mui

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
}

object MuiColor {
  final val DEFAULT = "default"
  final val INHERIT = "inherit"
  final val PRIMARY = "primary"
  final val ACCENT = "accent"
  final val CONTRAST = "contrast"
}