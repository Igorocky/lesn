package app.actions

import app.components.custom.userspage.UsersPageActions
import shared.dto.User

trait UsersPageActionsImpl extends UsersPageActions {self: GlobalContext =>
  override def userCreated(user: User) = modUsersPageMem(_.copy(users = usersPageMem.users.map(user :: _)))
}
