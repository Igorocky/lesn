package app.components.forms

import app.components.semanticui.{Color, Container, Message}
import japgolly.scalajs.react
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import shared.forms.{FormData, FormMethods}

object FormErrors {
  case class Props(formMethods: FormMethods[_], formData: FormData[_])

  protected case class State()

  def apply()(implicit formCommonParams: FormCommonParams[_,_]) = comp(Props(
    formMethods = formCommonParams.formMethods,
    formData = formCommonParams.formData
  ))

  private lazy val comp = react.ScalaComponent.builder[Props](this.getClass.getName)
    .initialState(State())
    .renderBackend[Backend]
    .build

  protected class Backend($: BackendScope[Props, State]) {
    def render(implicit props: Props, s: State) = Container()(
      (props.formData.generalErrors ++ getErrorsPerField).zipWithIndex.toVdomArray{case (msg,idx)=>
        Message(key = idx, color = Color.Red)(msg)
      }
    )

    def getErrorsPerField(implicit props: Props): Seq[String] = for {
      (fieldName, fieldErrors) <- props.formData.errors.toSeq
      field <- props.formMethods.allElems
      if field.name == fieldName
      error <- fieldErrors
    } yield s"${field.label}: ${error}"
  }


}
