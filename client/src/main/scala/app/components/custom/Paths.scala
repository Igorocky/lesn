package app.components.custom

sealed trait Path
case object DevHome extends Path
case object Login extends Path
case object Users extends Path
case object MuiLogin extends Path