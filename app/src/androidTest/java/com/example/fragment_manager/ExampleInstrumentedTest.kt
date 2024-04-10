package com.example.fragment_manager

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
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
}