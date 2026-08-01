fun main(args : Array<String>){
    val source = "let x = 5*3+2;x+3;"
    val tokens :List<Token> = Lexer(source).scanTokens()
    for (token in tokens){
        println(token)
    }
    println("================")
    val program : List<Stmt> =Parser(tokens).parse()
    program.forEach {currentStatement:Stmt->printstmt(currentStatement,0)}
 /*   println("=====================")
    val instruction : List<Instruction> = Compiler().compile(program)
    instruction.forEach { instruction :Instruction-> println(instruction) }
    println("================")
    val machine = Machine()
    for (instruction in instruction){
        machine.exectue(instruction)

    }
    println("Final Stack: [${machine.pop()}]")
*/
}
fun printstmt(stmt:Stmt,indent:Int){
    val padding: String ="    ".repeat(indent)
    when (stmt){
        is Stmt.ExpressionStmt -> {
            println("${padding}ExpressionStmt")
            Print(stmt.expression,indent+1)
        }
        is Stmt.VarDeclaration -> {
            println("${padding}VarDeclaration named ${stmt.name}")
            Print(stmt.initializer,indent+1)
        }
}}
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

    else -> {

    }}
}