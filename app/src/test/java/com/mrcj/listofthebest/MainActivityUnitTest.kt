package com.mrcj.listofthebest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mrcj.listofthebest.rest.Repositories
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityUnitTest {

    private lateinit var mainActivity: MainActivity

    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repository: Repositories

    @BeforeClass
    fun setUp() {
        mainActivity = MainActivity()
    }

    @Test
    fun open() {

//        Mockito.when(mainActivity.getCallBack())

//        assertEquals(1, mainActivity.page)
    }
}