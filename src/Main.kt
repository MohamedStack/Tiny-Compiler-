fun main(args : Array<String>){
    val source = "6 + 4"
    val tokens :List<Token> = Lexer(source).scanTokens()
    println(tokens)
}