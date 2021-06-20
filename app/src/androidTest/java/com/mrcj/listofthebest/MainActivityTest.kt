package com.mrcj.listofthebest

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    private lateinit var mainActivity: MainActivity

    @Before
    fun createLogHistory() {
        mainActivity = MainActivity()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.mrcj.listofthebest.MainActivity", appContext.packageName)
    }

    @Test
    fun useInit() {
        Assert.assertEquals(Retrofit::class, mainActivity.getCallBack())
    }
}