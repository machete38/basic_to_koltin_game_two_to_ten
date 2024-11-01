import kotlin.system.exitProcess

fun main() {
    val game = object: Game(){

        override fun textInput(type: Int, B: Int?) {
            this.continueThread(readln(), type, B)
        }

        override fun textOutput(text: String) {
            println(text)

        }

        override fun finish() {
           exitProcess(0)
        }

    }
}


