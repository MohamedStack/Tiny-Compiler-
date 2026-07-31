class Compiler {
    fun compile(expr: Expr):List<Instruction>{
        val instructions: MutableList<Instruction> = mutableListOf()
        emit(expr,instructions)
        return instructions
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
        }
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