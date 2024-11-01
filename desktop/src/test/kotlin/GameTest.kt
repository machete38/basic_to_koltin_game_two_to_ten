import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GameTest {

    @Test
    fun testGameFlow() {
        val input = "50\nY\nY\nY\nN\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        var gameFinished = false
        val game = object : Game() {
            override fun textInput(type: Int, B: Int?) {
                val input = readlnOrNull() ?: ""
                if (input.isNotEmpty()) {
                    this.continueThread(input, type, B)
                } else {
                    finish()
                }
            }
            override fun textOutput(text: String) {
                println(text)
            }
            override fun finish() {
                gameFinished = true
            }
        }

        game.play()

        val output = outputStream.toString()

        assertTrue(output.contains("PLACE YOUR BET"))
        assertTrue(output.contains("YOUR 'LUCKY LIMIT' CARD IS A"))
        assertTrue(output.contains("HERE WE GO"))
        assertTrue(output.contains("CARD #1 IS A"))
        assertTrue(output.contains("YOUR TOTAL IS"))
        assertTrue(output.contains("DO YOU WANT TO CONTINUE"))

        assertTrue(gameFinished)

        assertNotEquals(200, game.M)
    }
}