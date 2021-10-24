import java.util

sealed  trait  Expression

case class Number[A](value:A) extends Expression
case class Plus(l1:Expression,l2:Expression) extends Expression
case class Minus(l1:Expression,l2:Expression) extends Expression

object  ExpressionEvaluator{
  def  value(expression: Expression):Any = expression match {
    case Number(value) => value
    case Plus(l1, l2) =>value(l1) + value(l2)
    case Minus(l1, l2) = value(l1) - value(l2)
  }
}

util.ArrayList