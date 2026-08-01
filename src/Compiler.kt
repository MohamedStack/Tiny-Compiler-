class Compiler {
    private  val locals :MutableMap<String,Int> = mutableMapOf()
    private var nextLocalSlot:Int = 0
    fun compile(statements: List<Stmt>):List<Instruction>{
        val instructions: MutableList<Instruction> = mutableListOf()
        statements.forEach {
            statement->emit(statement,instructions)
        }
        return instructions
    }
    private  fun emit(stmt:Stmt,instructions: MutableList<Instruction>){
        when(stmt){
            is Stmt.ExpressionStmt ->{
                emit(stmt.expression,instructions)

            }
            is Stmt.VarDeclaration ->{
                if (locals.containsKey(stmt.name)){
                    error("Variable ${stmt.name} already exists")
                }
                emit(stmt.initializer,instructions)
                val slot:Int =
            }
        }
    }
    private fun  emit(expr:Expr,instructions: MutableList<Instruction>){
        when(expr){
            is Expr.NumberLiteral -> {
                instructions.add(Instruction.PushInt(expr.value))
            }
            is Expr.Binary -> {
                emit(expr.left,instructions)
                emit(expr.right,instructions)
                instructions.add(instructionForOperator(expr.operator))
            }

        else -> {

        }}
    }
    private  fun instructionForOperator(operator:Token):Instruction{
        return when (operator.type){
            TokenType.PLUS -> Instruction.Add
            TokenType.MINUS -> Instruction.Sub
            TokenType.STAR -> Instruction.Mul
            TokenType.SLASH -> Instruction.Div
            else -> error("Unknown operator")

        }
    }
}