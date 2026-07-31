import com.sun.tools.javac.jvm.ByteCodes.pop

class Machine {
    private val stack : MutableList<Int> = mutableListOf()
    fun exectue(instructions:Instruction) {
        when (instructions){
            is Instruction.PushInt -> stack.add(instructions.value)
            Instruction.Add -> {
                val right  = pop()
                val left = pop()
                stack.add(left + right)
            }
            Instruction.Sub ->{
                val right  = pop()
                val left = pop()
                stack.add(left - right)

            }
            Instruction.Mul -> {
                val right  = pop()
                val left = pop()
                stack.add(left * right)
            }
            Instruction.Div ->{
                val right  = pop()
                val left = pop()
                stack.add(left / right)
            }
        }
    }
    fun pop(): Int {
        if (stack.isEmpty()) {
            error("Stack underflow")
        }
        return stack.removeAt(stack.lastIndex)
    }
}