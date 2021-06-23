import com.mrcj.listofthebest.MainActivity
import android.R
import android.os.Build

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityUnitTest {

    @Test
    fun `On load list of the best`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)

        assertNotNull(activity.projectsAdapter)
        assertEquals(activity.page, 1)
    }
}