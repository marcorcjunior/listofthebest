package com.mrcj.listofthebest

import android.content.pm.ActivityInfo
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @get:Rule
    val mainActivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun litaScrollInfinity() {
        onView(isRoot()).perform(waitFor(5000))

        for (i in 1..8) {
            onView(withId(R.id.rv_list_projects)).perform(swipeUp())
            onView(isRoot()).perform(waitFor(2000))
        }
    }

    @Test
    fun listScrollInfiniteReturn() {
        onView(isRoot()).perform(waitFor(5000))

        for (i in 1..8) {
            onView(withId(R.id.rv_list_projects)).perform(swipeUp())
            onView(isRoot()).perform(waitFor(2000))
        }

        for (i in 1..8) {
            onView(withId(R.id.rv_list_projects)).perform(swipeDown())
            onView(isRoot()).perform(waitFor(2000))
        }
    }

    @Test
    fun rotateScreen() {
        onView(isRoot()).perform(waitFor(5000))
        mainActivity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        for (i in 1..4) {
            onView(withId(R.id.rv_list_projects)).perform(swipeUp())
            onView(isRoot()).perform(waitFor(2000))
        }

        onView(isRoot()).perform(waitFor(4000))
        mainActivity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }

    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "Espere por $delay milesimo de segundos"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}