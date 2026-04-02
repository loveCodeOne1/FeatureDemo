package com.zd.featuredemo.sealed

sealed class Expr{
    data class Num(val value:Int): Expr()
    data class Sum(val left: Expr,val right: Expr): Expr()
    data class Multiply(val left: Expr,val right: Expr): Expr()
}
fun eval(expr: Expr) :Int = when(expr){
    is Expr.Num-> expr.value
    is Expr.Sum-> eval(expr.left) + eval(expr.right)
    is Expr.Multiply -> eval(expr.left) * eval(expr.right)
}