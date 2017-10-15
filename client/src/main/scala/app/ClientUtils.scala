package app

import scala.scalajs.js

object ClientUtils {
  def mergeJSObjects(objs: js.Object*): js.Object = {
    val result = js.Dictionary.empty[Any]
    for (source <- objs) {
      for ((key, value) <- source.asInstanceOf[js.Dictionary[Any]])
        result(key) = value
    }
    result.asInstanceOf[js.Object]
  }
}
