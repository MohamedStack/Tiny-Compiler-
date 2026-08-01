sealed class Instruction {
    data class PushInt(val value:Int):Instruction()
    data object Add : Instruction()
    data object Sub : Instruction()
    data object Mul : Instruction()
    data object Div : Instruction()
    data class LoadLocal(val slot:Int):Instruction()
    data class StoreLocal(val slot:Int):Instruction()
}