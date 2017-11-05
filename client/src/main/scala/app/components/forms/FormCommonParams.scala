package app.components.forms

import app.components.custom.WindowFunc
import app.components.forms.FormCommonParams.SubmitFunction
import japgolly.scalajs.react.{Callback, _}
import shared.forms.Forms.SubmitResponse
import shared.forms.InputTransformation.Message
import shared.forms._
import shared.messages.Messages

import scala.util.{Failure, Success, Try}

object FormCommonParams {
  type SubmitFunction[F,S] = F => (Try[SubmitResponse[F,S]] => Callback) => Callback
}

case class FormCommonParams[T, S](
                                   formMethods: FormMethods[T],
                                   formData: FormData[T],
                                   onChange: FormData[T] => CallbackTo[FormData[T]],
                                   waitText: Message = lang => Messages.pleaseWait(lang) + "...",
                                   beforeSubmit: Callback = Callback.empty,
                                   submitFunction: SubmitFunction[T,S],
                                   onSubmitSuccess: S => Callback,
                                   onSubmitFormCheckFailure: Callback = Callback.empty,
                                   editMode: Boolean = false,
                                   windowFunc: WindowFunc
                           ) {

  lazy val submit: Callback = onChange(formMethods.validate(formData)) >>= { fd =>
    if (fd.hasErrors) {
      Callback.empty
    } else {
      windowFunc.openWaitPane(waitText(fd.language)) >> beforeSubmit >> submitFunction(fd.data){
        case Success(Left(newFormData)) => onChange(newFormData) >> onSubmitFormCheckFailure >> windowFunc.closeWaitPane
        case Success(Right(obj)) => onSubmitSuccess(obj) >> windowFunc.closeWaitPane
        case Failure(throwable) => windowFunc.showError(throwable, windowFunc.closeWaitPane)
      }
    }
  }

  def valueWasChanged[F](field: FormField[T, F])(newValue: F): Callback = onChange(field.set(newValue, formData)).void
}
