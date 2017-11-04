package app

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

class InputFormMacroImpl(val c: Context) {
  import c.universe._

  def field[T: c.WeakTypeTag,F](label: c.Expr[String], get: c.Expr[T => F]) = {
    val q"$_[$fieldType]($label, _.$fieldName)" = c.macroApplication
    val fieldNameStr = fieldName.toString()
    q"fieldFromGetterAndSetter($fieldNameStr, $label, _.$fieldName, (o: ${c.weakTypeOf[T]}, v: $fieldType) => o.copy($fieldName = v))"
  }

}

trait InputFormUtils[T,V[_],FF[T,_]] {
  protected def fieldFromGetterAndSetter[F](name: String, label: String, get: T => F, set: (T,F) => T)(v:V[F]):FF[T,F]
  protected def field[F](label: String, get: T => F): V[F] => FF[T,F] = macro InputFormMacroImpl.field[T,F]
}