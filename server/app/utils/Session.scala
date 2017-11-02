package utils

import shared.messages.Language
import shared.messages.Languages.EN

case class Session(sessionId: String = "#", userId: Long = -1, language: Language = EN)

object Session {
  val SESSION_ID = "session-id"
}