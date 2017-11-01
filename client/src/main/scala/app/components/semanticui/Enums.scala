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

object IconPosition extends Enumeration {
  val Left, Right = Value

  def toStr(v: Value) = v match {
    case Left => "left"
    case Right => "right"
  }
}
