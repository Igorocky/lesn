package app.components.mui

import app.ClientUtils._
import japgolly.scalajs.react
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{Callback, Children}

import scala.scalajs.js

case class AlignContent(strVal: String)
object AlignContentValues {
  val ALIGN_C_STRETCH = AlignContent("stretch")
  val ALIGN_C_CENTER = AlignContent("center")
  val ALIGN_C_FLEX_START = AlignContent("flex-start")
  val ALIGN_C_FLEX_END = AlignContent("flex-end")
  val ALIGN_C_SPACE_BETWEEN = AlignContent("space-between")
  val ALIGN_C_SPACE_AROUND = AlignContent("space-around")
}

case class AlignItems(strVal: String)
object AlignItemsValues {
  val ALIGN_I_FLEX_START = AlignItems("flex-start")
  val ALIGN_I_CENTER = AlignItems("center")
  val ALIGN_I_FLEX_END = AlignItems("flex-end")
  val ALIGN_I_STRETCH = AlignItems("stretch")
  val ALIGN_I_BASELINE = AlignItems("baseline")
}

case class ContentDirection(strVal: String)
object ContentDirectionValues {
  val DIRECTION_ROW = ContentDirection("row")
  val DIRECTION_ROW_REVERSE = ContentDirection("row-reverse")
  val DIRECTION_COLUMN = ContentDirection("column")
  val DIRECTION_COLUMN_REVERSE = ContentDirection("column-reverse")
}

case class Justify(strVal: String)
object JustifyValues {
  val JUSTIFY_FLEX_START = Justify("flex-start")
  val JUSTIFY_CENTER = Justify("center")
  val JUSTIFY_FLEX_END = Justify("flex-end")
  val JUSTIFY_SPACE_BETWEEN = Justify("space-between")
  val JUSTIFY_SPACE_AROUND = Justify("space-around")
}

case class Spacing(intVal: Int)
object SpacingValues {
  val S0 = Spacing(0)
  val S8 = Spacing(8)
  val S16 = Spacing(16)
  val S24 = Spacing(24)
  val S40 = Spacing(40)
}

case class Wrap(strVal: String)
object WrapValues {
  val NOWRAP = Wrap("nowrap")
  val WRAP = Wrap("wrap")
  val WRAP_REVERSE = Wrap("wrap-reverse")
}

object Grid {
  val component = react.JsComponent[js.Object, Children.Varargs, Null](MuiComponents.Grid)

  def apply(alignContent: js.UndefOr[AlignContent] = js.undefined,
            alignItems: js.UndefOr[AlignItems] = js.undefined,
            container: js.UndefOr[Boolean] = js.undefined,
            direction: js.UndefOr[ContentDirection] = js.undefined,
            item: js.UndefOr[Boolean] = js.undefined,
            justify: js.UndefOr[Justify] = js.undefined,
            spacing: js.UndefOr[Spacing] = js.undefined,
            wrap: js.UndefOr[Wrap] = js.undefined,
            xs: js.UndefOr[Int] = js.undefined,
            sm: js.UndefOr[Int] = js.undefined,
            md: js.UndefOr[Int] = js.undefined,
            lg: js.UndefOr[Int] = js.undefined,
            xl: js.UndefOr[Int] = js.undefined,
           )(children: VdomNode*) = {
    val props = js.Dynamic.literal()
      .merge("alignContent", alignContent, alignContent.map(_.strVal))
      .merge("alignItems", alignItems, alignItems.map(_.strVal))
      .merge("direction", direction, direction.map(_.strVal))
      .merge("justify", justify, justify.map(_.strVal))
      .merge("spacing", spacing, spacing.map(_.intVal))
      .merge("wrap", wrap, wrap.map(_.strVal))
      .merge("container", container)
      .merge("item", item)
      .merge("xs", xs)
      .merge("sm", sm)
      .merge("md", md)
      .merge("lg", lg)
      .merge("xl", xl)
    component(props)(children:_*)
  }
}

