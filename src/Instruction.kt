sealed class Instruction {
    data class PushInt(val value:Int):Instruction()
    data object Add : Instruction()
    data object Sub : Instruction()
    data object Mul : Instruction()
    data object Div : Instruction()
}