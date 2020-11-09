package com.yahancheng.timefighter

import android.os.Build
import android.os.Bundle
import android.widget.Button
import org.junit.After
import org.junit.Before
import org.junit.Test
import android.widget.TextView

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)

class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun startsWithZeroInitialScore() {
        //Given
        val systemUnderTest = `setUpTestableActivity()`()
        val scoreTextView = systemUnderTest?.findViewById(R.id.gameScoreTextView) as TextView
        //Then
        assertEquals("Your Score: 0", scoreTextView.text)
    }
    @Test
    fun increaseScore_whenButtonPressed() {
        val systemUnderTest = `setUpTestableActivity()`()
        val scoreTextView = systemUnderTest?.findViewById(R.id.gameScoreTextView) as TextView
        val tapMeButton = systemUnderTest?.findViewById(R.id.tapMeButton) as Button

        //When
        tapMeButton.performClick()

        //Then
        assertEquals("Your Score: 1", scoreTextView.text)
    }

    @Test
    fun recreatesActivity() {
        var controller = Robolectric.buildActivity(MainActivity::class.java)
                .create()
                .resume()
                .visible()
        var systemUnderTest = controller.get()

        var ScoreTextView = systemUnderTest.findViewById(R.id.gameScoreTextView) as TextView
        val TimeTextView = systemUnderTest.findViewById(R.id.timeLeftTextView) as TextView
        val tapMeButton = systemUnderTest.findViewById(R.id.tapMeButton) as Button

        tapMeButton.performClick()
        tapMeButton.performClick()
        tapMeButton.performClick()

        assertEquals("Your Score: 3", ScoreTextView.text)

        val bundle = Bundle()
        controller
                .saveInstanceState(bundle)
                .pause()
                .stop()
                .destroy()

        // Bring up a new activity
        controller = Robolectric.buildActivity(MainActivity::class.java)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible()

        val newSystem = controller.get()
        ScoreTextView = newSystem.findViewById(R.id.gameScoreTextView) as TextView
        print(9 / 3 * 3)

        assertEquals("Your Score: 3", ScoreTextView.text)
    }



    private fun `setUpTestableActivity()`(): MainActivity? {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .visible()
        val systemUnderTest = controller.get()
        return systemUnderTest
    }


}