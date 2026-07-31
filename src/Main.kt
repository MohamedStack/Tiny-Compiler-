fun main(args : Array<String>){
    val source = "6+(5*3)/2"
    val tokens :List<Token> = Lexer(source).scanTokens()
    for (token in tokens){
        println(token)
    }
    println("================")
    val expression =Parser(tokens).parse()
    Print(expression,0)
}
fun Print(expr:Expr,indent:Int){
    val padding: String ="    ".repeat(indent)
    when (expr){
        is Expr.NumberLiteral ->{
            println("${padding} Number(${expr.value})")
        }
        is Expr.Binary -> {
            println("${padding}Binary(${expr.operator.type})")
            Print(expr.left,indent+1)
            Print(expr.right,indent+1)


        }
    }
}