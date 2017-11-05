package app.components.semanticui

object VerticalAlign extends Enumeration {
  val Bottom, Middle, Top = Value

  def toStr(v: Value) = v match {
    case VerticalAlign.Bottom => "bottom"
    case VerticalAlign.Middle => "middle"
    case VerticalAlign.Top => "top"
  }
}

object TextAlign extends Enumeration {
  val Left, Center, Right, Justified = Value

  def toStr(v: Value) = v match {
    case Left => "left"
    case Center => "center"
    case Right => "right"
    case Justified => "justified"
  }
}

object Color extends Enumeration {
  val Red, Orange, Yellow, Olive, Green, Teal, Blue, Violet, Purple, Pink, Brown, Grey, Black = Value

  def toStr(v: Value) = v match {
    case Red => "red"
    case Orange => "orange"
    case Yellow => "yellow"
    case Olive => "olive"
    case Green => "green"
    case Teal => "teal"
    case Blue => "blue"
    case Violet => "violet"
    case Purple => "purple"
    case Pink => "pink"
    case Brown => "brown"
    case Grey => "grey"
    case Black => "black"
  }
}

object Size extends Enumeration {
  val Tiny, Small, Medium, Large, Huge = Value

  def toStr(v: Value) = v match {
    case Tiny => "tiny"
    case Small => "small"
    case Medium => "medium"
    case Large => "large"
    case Huge => "huge"
  }
}

object HeaderSize extends Enumeration {
  val Mini, Tiny, Small, Medium, Large, Big, Huge, Massive = Value

  def toStr(v: Value) = v match {
    case Mini => "mini"
    case Tiny => "tiny"
    case Small => "small"
    case Medium => "medium"
    case Large => "large"
    case Big => "big"
    case Huge => "huge"
    case Massive => "massive"
  }
}

object ButtonSize extends Enumeration {
  val Mini, Tiny, Small, Large, Big, Huge, Massive = Value

  def toStr(v: Value) = v match {
    case Mini => "mini"
    case Tiny => "tiny"
    case Small => "small"
    case Large => "large"
    case Big => "big"
    case Huge => "huge"
    case Massive => "massive"
  }
}

object Position extends Enumeration {
  val Left, Right = Value

  def toStr(v: Value) = v match {
    case Left => "left"
    case Right => "right"
  }
}

object Widths extends Enumeration {
  val Equal = Value

  def toStr(v: Value) = v match {
    case Equal => "equal"
  }
}

object PopupPosition extends Enumeration {
  val TopLeft, TopRight, BottomRight, BottomLeft, RightCenter, LeftCenter, TopCenter, BottomCenter = Value

  def toStr(v: Value) = v match {
    case TopLeft => "top left"
    case TopRight => "top right"
    case BottomRight => "bottom right"
    case BottomLeft => "bottom left"
    case RightCenter => "right center"
    case LeftCenter => "left center"
    case TopCenter => "top center"
    case BottomCenter => "bottom center"
  }
}

object Width extends Enumeration {
  val _1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16,
    One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven, Twelve, Thirteen, Fourteen, Fifteen, Sixteen = Value

  def toStr(v: Value) = v match {
    case _1 => "1"
    case _2 => "2"
    case _3 => "3"
    case _4 => "4"
    case _5 => "5"
    case _6 => "6"
    case _7 => "7"
    case _8 => "8"
    case _9 => "9"
    case _10 => "10"
    case _11 => "11"
    case _12 => "12"
    case _13 => "13"
    case _14 => "14"
    case _15 => "15"
    case _16 => "16"
    case One => "one"
    case Two => "two"
    case Three => "three"
    case Four => "four"
    case Five => "five"
    case Six => "six"
    case Seven => "seven"
    case Eight => "eight"
    case Nine => "nine"
    case Ten => "ten"
    case Eleven => "eleven"
    case Twelve => "twelve"
    case Thirteen => "thirteen"
    case Fourteen => "fourteen"
    case Fifteen => "fifteen"
    case Sixteen => "sixteen"
  }
}
