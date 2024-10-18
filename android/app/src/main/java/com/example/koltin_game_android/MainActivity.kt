package com.example.koltin_game_android

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var gameM = 200
    var gameD = 0
    var gameT = 0
    var gameO = (10 * Random.nextDouble()).toInt() + 25
    var gameN = (gameO * Random.nextDouble()).toInt() + gameO
    var gameR = (15 * Random.nextDouble()).toInt() / 100.0
    var gameS = (2 * Random.nextDouble()).toInt() + 1
    var gameE = if (gameS != 1) (gameN - (gameN * gameR)).toInt() else (gameN + (gameN * gameR)).toInt()
    var gameA = (9 * Random.nextDouble(11.0)).toInt() + 2
    lateinit var tv: TextView
    lateinit var et: EditText
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.textview)
        et = findViewById(R.id.editText)
        print_app_line("TWO TO TEN")
        print_app_line("CREATIVE COMPUTING  MORRISTOWN NEW JERSEY")
        print_app_line("")
        print_app_line("")
        print_app_line("")
        print_app_line("WELCOME TO THE GAME OF TWO TO TEN.  THAT NAME COMES FROM THE")
        print_app_line("SPECIAL 'DECK OF CARDS' USED. THERE ARE NO FACE CARDS - ONLY")
        print_app_line("THE CARDS 2-10.  THIS GAME IS EASY AND FUN TO PLAY IF YOU")
        print_app_line("UNDERSTAND WHAT YOU ARE DOING SO READ THE INSTRUCTIONS")
        print_app_line("CAREFULLY.")
        print_app_line("AT THE START OF THE GAME, YOU BET ON WINNING. TYPE IN ANY")
        print_app_line("NUMBER BETWEEN 0 AND 200.  I THEN PICK A RANDOM NUMBER")
        print_app_line("YOU ARE TO REACH BY THE SUM TOTAL OF MORE CARDS CHOSEN.")
        print_app_line("BECAUSE OF THE RARE CHANCE OF YOU GETTING TO THAT NUMBER")
        print_app_line("EXACTLY, YOU ARE GIVEN AN ALLOWANCE CARD.  THE OBJECT OF")
        print_app_line("THE GAME OF TO GET THE TOTAL OF CARDS WITHIN THE MYSTERY")
        print_app_line("NUMBER WITHOUT GOING OVER.")
        print_app_line("YOU ARE GIVEN A HINT AS TO WHAT THE NUMBER IS.  THIS IS NOT")
        print_app_line("THE EXACT NUMBER ONLY ONE CLOSE. ALL YOU DO IN THIS GAME IS")
        print_app_line("DECIDE WHEN TO STOP.  AT THIS POINT YOUR TOTAL IS COMPARED")
        print_app_line("WITH THE NUMBER AND YOUR WINNINGS ARE DETERMINED.")

        play()

    }

    private fun print_app_line(text: String?)
    {
        val newText = tv.text.toString()+"\n"+text
        tv.text = newText
    }

    private fun print_app_line()
    {
        val newText = tv.text.toString()+"\n"
        tv.text = newText
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun play()
    {
        gameD = 0
        gameT = 0
        gameO = (10 * Random.nextDouble()).toInt() + 25
        gameN = (gameO * Random.nextDouble()).toInt() + gameO
        gameR = (15 * Random.nextDouble()).toInt() / 100.0
        gameS = (2 * Random.nextDouble()).toInt() + 1
        gameE = if (gameS != 1) (gameN - (gameN * gameR)).toInt() else (gameN + (gameN * gameR)).toInt()
        gameA = (9 * Random.nextDouble(11.0)).toInt() + 2

        print_app_line("")
        print_app_line("PLACE YOUR BET ... YOU HAVE $$gameM TO SPEND.")
        et.requestFocus()
        read_app_line(1, null)
    }
    
    @RequiresApi(Build.VERSION_CODES.O)
    fun continueGame(B: Int) {
        print_app_line()
        print_app_line()

        gameD++
        val C = (9 * Random.nextDouble()).toInt() + 2
        print_app_line("CARD #$gameD IS A $C. YOU ARE TRYING TO COME NEAR $gameE")
        gameT += C

        if (gameT <= gameN) {
            print_app_line("YOUR TOTAL IS $gameT  DO YOU WANT TO CONTINUE")
            read_app_line(2, B)

        } else {
            print_app_line("YOUR TOTAL IS OVER THE NUMBER $gameN; AN AUTOMATIC LOSS!")
            gameM -= B

            print_app_line("YOU NOW HAVE $$gameM IN CASH TO BET IN THE NEXT GAME!")
            if (gameM <= 0) {
                print_app_line("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
                hideKeyboard(et)
            } else {
                print_app_line("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                read_app_line(3, null)
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun read_app_line(type: Int, B: Int?){
        et.requestFocus()
        et.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text = et.text.toString()
                et.text.clear()
                print_app_line(text)
                print_app_line("")
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                when(type){
                    1 -> {
                        var B = text.toInt()

                        if (B < 0) {
                            print_app_line("YOU MAY NOT BET AGAINST YOURSELF.")
                        } else if (gameM >= B) {
                            print_app_line("YOUR 'LUCKY LIMIT' CARD IS A $gameA")
                            print_app_line("YOU MUST COME WITHIN $gameA WITHOUT GOING OVER TO WIN.")
                            print_app_line("")
                            print_app_line("HERE WE GO")
                            continueGame(B)
                        } else {
                            print_app_line("YOU CAN'T BET MORE THAT YOU'VE GOT!")
                        }
                    }
                    2 ->
                    {
                        val Q = text
                            if (Q.startsWith("Y", ignoreCase = true)) {
                                if (B != null) {
                                    continueGame(B)
                                }
                            } else if (gameT < gameN - gameA || gameT > gameN) {
                                print_app_line("YOU BLEW IT!  THE NUMBER WAS $gameN, OUTSIDE YOUR LIMIT BY ${gameN - gameA - gameT}")
                                print_app_line()
                                if (B != null) {
                                    gameM -= B
                                }
                                print_app_line("YOU NOW HAVE $$gameM IN CASH TO BET IN THE NEXT GAME!")
                                if (gameM <= 0) {
                                    print_app_line("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
                                    hideKeyboard(et)
                                } else {
                                    print_app_line("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                                    read_app_line(3, null)
                                }
                            } else {
                                print_app_line("YOU WIN!  THE NUMBER WAS $gameN; YOUR GUESS TOTAL WAS $gameT")
                                print_app_line("WITHIN YOUR LIMIT CARD.")
                                if (B != null) {
                                    gameM += B
                                }
                                print_app_line("YOU NOW HAVE $$gameM IN CASH TO BET IN THE NEXT GAME!")
                                if (gameM <= 0) {
                                    print_app_line("YOU ARE BROKE!! YOU MAY NOT PLAY ANYMORE!!")
                                    hideKeyboard(et)
                                } else {
                                    print_app_line("WOULD YOU LIKE TO PLAY THE NEXT GAME")
                                    read_app_line(3, null)
                                }
                            }
                    }
                    3 ->
                    {
                        val Q = text
                            if (Q.startsWith("Y", ignoreCase = true)) {
                                play()
                            } else {
                                print_app_line("HOPE YOU HAD FUN.")
                                hideKeyboard(et)
                            }
                    }
                }

            }
            false
        })

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        et.clearFocus()
        et.focusable = View.NOT_FOCUSABLE
    }



}