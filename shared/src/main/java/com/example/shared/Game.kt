import kotlin.random.Random


abstract class Game {
    var M = 200
    var D = 0
    var T = 0
    var O = (10 * Random.nextDouble()).toInt() + 25
    var N = (O * Random.nextDouble()).toInt() + O
    var R = (15 * Random.nextDouble()).toInt() / 100.0
    var S = (2 * Random.nextDouble()).toInt() + 1
    var E = if (S != 1) (N - (N * R)).toInt() else (N + (N * R)).toInt()
    var A = (9 * Random.nextDouble(11.0)).toInt() + 2

    abstract fun textInput(type: Int, B: Int?)
    abstract fun textOutput(text: String)
    abstract fun finish()

    fun continueThread(text: String, type: Int, B: Int?)
    {
        when(type){
            1 -> {
                var B = text.toInt()

                if (B < 0) {
                    textOutput("YOU MAY NOT BET AGAINST YOURSELF.")
                } else if (M >= B) {
                    textOutput("YOUR 'LUCKY LIMIT' CARD IS A $A")
                    textOutput("YOU MUST COME WITHIN $A WITHOUT GOING OVER TO WIN.")
                    textOutput("")
                    textOutput("HERE WE GO")
                    continueGame(B)
                } else {
                    textOutput("YOU CAN'T BET MORE THAT YOU'VE GOT!")
                }
            }
            2 ->
            {
                val Q = text
                if (Q.startsWith("Y", ignoreCase = true)) {
                    if (B != null) {
                        continueGame(B)
                    }
                } else if (T < N - A || T > N) {
                    textOutput("YOU BLEW IT!  THE NUMBER WAS $N, OUTSIDE YOUR LIMIT BY ${N - A - T}")
                    textOutput("")
                    if (B != null) {
                        M -= B
                    }
                    textOutput("YOU NOW HAVE $$M IN CASH TO BET IN THE NEXT GAME!")
                    if (M <= 0) {
                        textOutput("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
                       finish()
                    } else {
                        textOutput("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                        textInput(3, null)
                    }
                } else {
                    textOutput("YOU WIN!  THE NUMBER WAS $N; YOUR GUESS TOTAL WAS $T")
                    textOutput("WITHIN YOUR LIMIT CARD.")
                    if (B != null) {
                        M += B
                    }
                    textOutput("YOU NOW HAVE $$M IN CASH TO BET IN THE NEXT GAME!")
                    if (M <= 0) {
                        textOutput("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
                       finish()
                    } else {
                        textOutput("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                        textInput(3, null)
                    }
                }
            }
            3 ->
            {
                val Q = text
                if (Q.startsWith("Y", ignoreCase = true)) {
                    play()
                } else {
                    textOutput("HOPE YOU HAD FUN.")
                   finish()
                }
            }
        }
    }

    init {
        textOutput("TWO TO TEN")
        textOutput("CREATIVE COMPUTING  MORRISTOWN NEW JERSEY")
        textOutput("")
        textOutput("")
        textOutput("")
        textOutput("WELCOME TO THE GAME OF TWO TO TEN.  THAT NAME COMES FROM THE")
        textOutput("SPECIAL 'DECK OF CARDS' USED. THERE ARE NO FACE CARDS - ONLY")
        textOutput("THE CARDS 2-10.  THIS GAME IS EASY AND FUN TO PLAY IF YOU")
        textOutput("UNDERSTAND WHAT YOU ARE DOING SO READ THE INSTRUCTIONS")
        textOutput("CAREFULLY.")
        textOutput("AT THE START OF THE GAME, YOU BET ON WINNING. TYPE IN ANY")
        textOutput("NUMBER BETWEEN 0 AND 200.  I THEN PICK A RANDOM NUMBER")
        textOutput("YOU ARE TO REACH BY THE SUM TOTAL OF MORE CARDS CHOSEN.")
        textOutput("BECAUSE OF THE RARE CHANCE OF YOU GETTING TO THAT NUMBER")
        textOutput("EXACTLY, YOU ARE GIVEN AN ALLOWANCE CARD.  THE OBJECT OF")
        textOutput("THE GAME OF TO GET THE TOTAL OF CARDS WITHIN THE MYSTERY")
        textOutput("NUMBER WITHOUT GOING OVER.")
        textOutput("YOU ARE GIVEN A HINT AS TO WHAT THE NUMBER IS.  THIS IS NOT")
        textOutput("THE EXACT NUMBER ONLY ONE CLOSE. ALL YOU DO IN THIS GAME IS")
        textOutput("DECIDE WHEN TO STOP.  AT THIS POINT YOUR TOTAL IS COMPARED")
        textOutput("WITH THE NUMBER AND YOUR WINNINGS ARE DETERMINED.")

        play()
    }
    fun play()
    {
        D = 0
        T = 0
        O = (10 * Random.nextDouble()).toInt() + 25
        N = (O * Random.nextDouble()).toInt() + O
        R = (15 * Random.nextDouble()).toInt() / 100.0
        S = (2 * Random.nextDouble()).toInt() + 1
        E = if (S != 1) (N - (N * R)).toInt() else (N + (N * R)).toInt()
        A = (9 * Random.nextDouble(11.0)).toInt() + 2

        textOutput("")
        textOutput("PLACE YOUR BET ... YOU HAVE $$M TO SPEND.")
        textInput(1, null)

    }

    fun continueGame(B: Int) {
        textOutput("")
        textOutput("")

        D++
        val C = (9 * Random.nextDouble()).toInt() + 2
        textOutput("CARD #$D IS A $C. YOU ARE TRYING TO COME NEAR $E")
        T += C

        if (T <= N) {
            textOutput("YOUR TOTAL IS $T  DO YOU WANT TO CONTINUE")
            textInput(2, B)

        } else {
            textOutput("YOUR TOTAL IS OVER THE NUMBER $N; AN AUTOMATIC LOSS!")
            M -= B

            textOutput("YOU NOW HAVE $$M IN CASH TO BET IN THE NEXT GAME!")
            if (M <= 0) {
                textOutput("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
               finish()
            } else {
                textOutput("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                textInput(3, null)
            }
        }
    }
}