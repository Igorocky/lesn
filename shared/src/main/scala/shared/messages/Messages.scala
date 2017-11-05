package shared.messages

import Languages._

object Messages {
  def title(implicit lang: Language) = Msg(PL -> "Nazwa", RU -> "Название", EN -> "Title")
  def content(implicit lang: Language) = Msg(PL -> "Text", RU -> "Текст", EN -> "Content")

  def maxLength(max: Int)(implicit lang: Language) = Msg(
    PL -> s"maksymalna długość: $max",
    RU -> s"максимальная длина: $max",
    EN -> s"maximum length: $max"
  )

  def fieldShouldNotBeEmpty(implicit lang: Language) = Msg(
    RU -> s"поле должно быть заполнено",
    EN -> s"field should not be empty"
  )

  def fieldShouldContainOnlyDigits(implicit lang: Language) = Msg(
    RU -> s"поле должно содержать только цифры",
    EN -> s"field should contain only digits"
  )

  def accessDenied(implicit lang: Language) = Msg(
    RU -> s"доступ запрещён",
    EN -> s"access denied"
  )

  def fieldsShouldHaveSameValue(field1: String, field2: String)(implicit lang: Language) = Msg(
    RU -> s"поля '$field1' и '$field2' должны иметь одинаковое значение",
    EN -> s"'$field1' and '$field2' fields should have same value",
  )
}
