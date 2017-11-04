package app.actions

import app.components.custom.WindowFunc
import app.components.custom.userspage.UsersTableActions

trait UsersTableActionsImpl extends UsersTableActions {self: GlobalContext with WindowFunc =>
  override def loadUsers = serverApi.post(_.listUsers(), showError){
    case Right(users) => modUsersPageMem(_.copy(users = Some(users)))
    case _ => modUsersPageMem(_.copy(users = Some(Nil)))
  }
}
