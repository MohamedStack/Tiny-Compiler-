sealed class Instruction {
    data class PushInt(val value:Int):Instruction()
    data object Add : Instruction(){
        override fun toString() = "Add"
    }
    data object Sub : Instruction(){
        override fun toString() = "Sub"
    }
    data object Mul : Instruction(){
        override fun toString() = "Mul"
    }
    data object Div : Instruction(){
        override fun toString() = "Div"
    }
    data class LoadLocal(val slot:Int):Instruction()
    data class StoreLocal(val slot:Int):Instruction()
}