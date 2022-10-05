package com.yuyu.momo

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeFrgInstrumentedTest {

    private lateinit var uidevice: UiDevice
    companion object {
        const val PKG_NAME = "com.yuyu.momo"
    }

    @Before
    fun init() {
        uidevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PKG_NAME, appContext.packageName)
    }

    @Test
    fun testMainPage() {
        uidevice.pressHome()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            PKG_NAME
        )?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        uidevice.wait(
            Until.hasObject(By.pkg(PKG_NAME).depth(0)),
            1000
        )

        uidevice.wait(Until.hasObject(By.text("臺北市立動物園")), 500)

        uidevice.waitForWindowUpdate(PKG_NAME, 5000)
        val list = uidevice.findObjects(By.desc("park"))

        for (index in 0 until list.size) {
            val newList = uidevice.findObjects(By.desc("park"))
            val titleTv = newList[index].text
            newList[index].click()
            uidevice.waitForWindowUpdate(PKG_NAME, 1000)
            val title = uidevice.findObject(By.res(PKG_NAME, "title_tv"))
            assertThat(title.text).isEqualTo(titleTv)
            uidevice.pressBack()
            uidevice.waitForWindowUpdate(PKG_NAME, 1000)
        }
    }
}