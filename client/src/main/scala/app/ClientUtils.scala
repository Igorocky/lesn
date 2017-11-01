package app

import scala.scalajs.js

object ClientUtils {
  implicit class jsObjOps[T](jsObj: js.Object) {
    def merge[A,B](name: String, undef: js.UndefOr[A], value: js.UndefOr[B]): js.Object = mergeJSObjects(
      jsObj,
      if (undef.isDefined) js.Dynamic.literal(
        name -> value.asInstanceOf[js.Any]
      ) else js.Dynamic.literal()
    )
    def merge[A](name: String, undef: js.UndefOr[A]): js.Object = merge(name, undef, undef)
  }

  def mergeJSObjects(objs: js.Object*): js.Object = {
    val result = js.Dictionary.empty[Any]
    for (source <- objs) {
      for ((key, value) <- source.asInstanceOf[js.Dictionary[Any]])
        result(key) = value
    }
    result.asInstanceOf[js.Object]
  }
}
