import kotlin.random.Random

var M = 200
var D = 0
var T = 0
var O = (10 * Random.nextDouble()).toInt() + 25
var N = (O * Random.nextDouble()).toInt() + O
var R = (15 * Random.nextDouble()).toInt() / 100.0
var S = (2 * Random.nextDouble()).toInt() + 1
var E = if (S != 1) (N - (N * R)).toInt() else (N + (N * R)).toInt()
var A = (9 * Random.nextDouble(11.0)).toInt() + 2

fun main() {
    println("TWO TO TEN")
    println("CREATIVE COMPUTING  MORRISTOWN NEW JERSEY")
    println()
    println()
    println()
    println("WELCOME TO THE GAME OF TWO TO TEN.  THAT NAME COMES FROM THE")
    println("SPECIAL 'DECK OF CARDS' USED. THERE ARE NO FACE CARDS - ONLY")
    println("THE CARDS 2-10.  THIS GAME IS EASY AND FUN TO PLAY IF YOU")
    println("UNDERSTAND WHAT YOU ARE DOING SO READ THE INSTRUCTIONS")
    println("CAREFULLY.")
    println("AT THE START OF THE GAME, YOU BET ON WINNING. TYPE IN ANY")
    println("NUMBER BETWEEN 0 AND 200.  I THEN PICK A RANDOM NUMBER")
    println("YOU ARE TO REACH BY THE SUM TOTAL OF MORE CARDS CHOSEN.")
    println("BECAUSE OF THE RARE CHANCE OF YOU GETTING TO THAT NUMBER")
    println("EXACTLY, YOU ARE GIVEN AN ALLOWANCE CARD.  THE OBJECT OF")
    println("THE GAME OF TO GET THE TOTAL OF CARDS WITHIN THE MYSTERY")
    println("NUMBER WITHOUT GOING OVER.")
    println("YOU ARE GIVEN A HINT AS TO WHAT THE NUMBER IS.  THIS IS NOT")
    println("THE EXACT NUMBER ONLY ONE CLOSE. ALL YOU DO IN THIS GAME IS")
    println("DECIDE WHEN TO STOP.  AT THIS POINT YOUR TOTAL IS COMPARED")
    println("WITH THE NUMBER AND YOUR WINNINGS ARE DETERMINED.")

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

    println()
    println("PLACE YOUR BET ... YOU HAVE $$M TO SPEND.")
    var B = readln().toInt()
    println()

    if (B < 0) {
        println("YOU MAY NOT BET AGAINST YOURSELF.")
    } else if (M >= B) {
        println("YOUR 'LUCKY LIMIT' CARD IS A $A")
        println("YOU MUST COME WITHIN $A WITHOUT GOING OVER TO WIN.")
        println()
        println("HERE WE GO")
        continueGame(B)
    } else {
        println("YOU CAN'T BET MORE THAT YOU'VE GOT!")
    }
}

fun continueGame(B: Int) {
    println()
    println()

    D++
    val C = (9 * Random.nextDouble()).toInt() + 2
    println("CARD #$D IS A $C. YOU ARE TRYING TO COME NEAR $E")
    T += C

    if (T <= N) {
        println("YOUR TOTAL IS $T  DO YOU WANT TO CONTINUE")
        val Q = readLine()!!
        println()

        if (Q.startsWith("Y", ignoreCase = true)) {
            continueGame(B)
        } else if (T < N - A || T > N) {
            println("YOU BLEW IT!  THE NUMBER WAS $N, OUTSIDE YOUR LIMIT BY ${N - A - T}")
            println()
            M -= B
        } else {
            println("YOU WIN!  THE NUMBER WAS $N; YOUR GUESS TOTAL WAS $T")
            println("WITHIN YOUR LIMIT CARD.")
            M += B
        }
    } else {
        println("YOUR TOTAL IS OVER THE NUMBER $N; AN AUTOMATIC LOSS!")
        M -= B
    }

    println("YOU NOW HAVE $$M IN CASH TO BET IN THE NEXT GAME!")
    if (M <= 0) {
        println("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
        System.exit(0)
    } else {
        println("WOULD YOU LIKE TO PLAY THE NEXT GAME")
        val Q = readLine()!!
        if (Q.startsWith("Y", ignoreCase = true)) {
            play()
        } else {
            println("HOPE YOU HAD FUN.")
            System.exit(0)
        }
    }
}

