package com.example.koltin_game_android

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testGameFlow() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("WELCOME TO THE GAME OF TWO TO TEN"))))

        onView(withId(R.id.editText))
            .perform(typeText("50"), pressImeActionButton())

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("YOUR 'LUCKY LIMIT' CARD IS A"))))

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("CARD #1 IS A"))))

        onView(withId(R.id.editText))
            .perform(typeText("Y"), pressImeActionButton())

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("CARD #2 IS A"))))

        onView(withId(R.id.editText))
            .perform(typeText("N"), pressImeActionButton())

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("YOU NOW HAVE $"))))

        onView(withId(R.id.editText))
            .perform(typeText("N"), pressImeActionButton())

        onView(withId(R.id.textview))
            .check(matches(withText(containsString("HOPE YOU HAD FUN"))))
    }
}