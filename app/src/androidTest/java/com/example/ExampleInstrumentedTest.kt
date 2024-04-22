package com.example

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fragment_manager.R
import com.example.fragment_manager.presentation.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test() {
        TabScreen.openFunnyFragmentBtn.click()
        FunnyScreen.funnyImage.isDisplayed()
        pressBack()
        TabScreen.openFunnyFragmentBtn.isDisplayed()
        TabScreen.openFunnyFragmentBtn.click()
        MainScreen.bottomNavigation.setSelectedItem(R.id.tab_2)
        TabScreen.openFunnyFragmentBtn.isDisplayed()
        MainScreen.bottomNavigation.setSelectedItem(R.id.tab_1)
        FunnyScreen.funnyImage.isDisplayed()
    }

    @Test
    fun testPassWordBetweenFragments() {
        TabScreen.somethingEt.typeText(TEST_WORD)
        TabScreen.openFunnyFragmentBtn.click()
        FunnyScreen.receivedWord.containsText(TEST_WORD)
    }

    private companion object {
        const val TEST_WORD = "test word"
    }
}