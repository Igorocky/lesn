package app.actions

import app.components.custom.NavigationBarActions
import japgolly.scalajs.react.Callback

trait NavigationBarActionsImpl extends NavigationBarActions {
  override def logout = Callback.empty
}
