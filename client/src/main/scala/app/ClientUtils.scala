package app

import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, CallbackTo}
import org.scalajs.dom.ext.Ajax
import upickle.default.{read, write}

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.util.{Failure, Success, Try}

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

  def post[T](url: String, data: Ajax.InputData)(f: Try[Either[String,String]] => CallbackTo[T]): CallbackTo[Future[T]] =
    CallbackTo.future {
      Ajax.post(url = url, data = data).map {
        r => Success(read[Either[String,String]](r.responseText))
      }.recover[Try[Either[String,String]]] {
        case throwable => Failure(throwable)
      }.map(f)
    }

  def post[I, O](url: String, path: String,
                 dataStr: String,
                 reader: String => O,
                 errHnd: Throwable => Callback
                )(s: O => Callback): Callback =
    Callback.future {
      Ajax.post(url = url, data = write((path, dataStr)))
        .map(resp => s(reader(resp.responseText)))
        .recover {
          case throwable => errHnd(throwable)
        }
    }

  def post[I, O](url: String, path: String,
                 dataStr: String,
                 reader: String => O
                )(s: Try[O] => Callback): Callback =
    Callback.future {
      Ajax.post(url = url, data = write((path, dataStr)))
        .map(resp => s(Success(reader(resp.responseText))))
        .recover {
          case throwable => s(Failure(throwable))
        }
    }

  def createWsClient[A](url: String): WsClient[A] = new WsClient[A] {
    override def doCall[O](path: String,
                           dataStr: String,
                           reader: String => O,
                           errHnd: Throwable => Callback): (O => Callback) => Callback = ClientUtils.post(
      url,
      path,
      dataStr,
      reader,
      errHnd
    )

    override def doCall[O](path: String,
                           dataStr: String,
                           reader: String => O): (Try[O] => Callback) => Callback = ClientUtils.post(
      url,
      path,
      dataStr,
      reader
    )
  }

  def stubWsClient[A](stubName: String): WsClient[A] = new WsClient[A] {
    override def doCall[O](path: String,
                           dataStr: String,
                           reader: String => O,
                           errHnd: Throwable => Callback): (O => Callback) => Callback = outputConsumer => Callback{
      println(s"stubbed $stubName.doCall invoked: path = '$path', dataStr = '$dataStr'")
    }

    override def doCall[O](path: String,
                           dataStr: String,
                           reader: String => O): (Try[O] => Callback) => Callback = outputConsumer => Callback{
      println(s"stubbed $stubName.doCall invoked: path = '$path', dataStr = '$dataStr'")
    }
  }

  def whenDefined[T](opt: Option[T])(f: T => VdomNode): VdomNode =
    if (opt.isDefined) f(opt.get) else VdomNode.cast("")

  def when(condition: Boolean)(elem: => VdomNode): VdomNode =
    if (condition) elem else VdomNode.cast("")
}
