package app.actions

import app.components.custom.WindowFunc
import app.components.custom.userspage.CreateUserFormActions
import app.components.forms.FormCommonParams.SubmitFunction
import shared.dto.{CreateUserRequest, User}

trait CreateUserFormActionsImpl extends CreateUserFormActions {self: GlobalContext with WindowFunc =>
  override def createUser: SubmitFunction[CreateUserRequest,User] = user => serverApi.post(_.createUser(user), showError)
}
