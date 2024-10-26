package com.example.koltin_game_android

import Game
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
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    lateinit var et: EditText
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.textview)
        et = findViewById(R.id.editText)
        val game = object: Game(){
            override fun textInput(type: Int, B: Int?) {
                et.requestFocus()
                et.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        val text = et.text.toString()
                        et.text.clear()
                        textOutput(text)
                        textOutput("")
                        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                        this.continueThread(text, type, B)

                    }
                    false
                })
            }


            override fun textOutput(text: String) {
                val newText = tv.text.toString()+"\n"+text
                tv.text = newText
            }

            override fun finish() {
               hideKeyboard(et)
            }

        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        et.clearFocus()
        et.focusable = View.NOT_FOCUSABLE
    }



}