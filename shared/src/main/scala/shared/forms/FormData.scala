package shared.forms

import shared.messages.Language

case class FormData[T](language: Language,
                       data: T,
                       errors: Map[String, List[String]] = Map(),
                       generalErrors: List[String] = Nil) {
  def hasErrors = errors.exists(_._2.nonEmpty) || generalErrors.nonEmpty
}