package app.components.custom

sealed trait MyPages
case object Root extends MyPages
case object LoginPage extends MyPages
case object MuiLoginPage extends MyPages
case object PageNotFound extends MyPages