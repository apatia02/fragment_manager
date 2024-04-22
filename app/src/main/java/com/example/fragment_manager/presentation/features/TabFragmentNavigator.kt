package com.example.fragment_manager.presentation.features

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.Stack

class TabFragmentNavigator(
    private val activity: AppCompatActivity, initialTab: String, private val fragmentContainer: Int
) {

    private val tabStacks: MutableMap<String, Stack<Fragment>> = mutableMapOf()

    private var currentTabId = initialTab

    private val fragmentManager = activity.supportFragmentManager

    fun replace(fragment: Fragment) {
        pushFragment(fragment)
        fragmentManager.beginTransaction().replace(fragmentContainer, fragment).commit()
    }

    fun switchTab(tabId: String, initialFragment: Fragment) {
        setCurrentTabId(tabId)
        val fragment = getUpperFragment() ?: initialFragment
        replace(fragment)
    }

    fun popBackStack() {
        val fragment = popFragment()
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(fragmentContainer, fragment).commit()
        } else {
            activity.finish()
        }
    }

    private fun setCurrentTabId(tabId: String) {
        currentTabId = tabId
    }

    private fun pushFragment(fragment: Fragment) {
        val tabBackStack = getBackStack()
        tabBackStack.push(fragment)
    }

    private fun popFragment(): Fragment? {
        val tabBackStack = getBackStack()
        return if (tabBackStack.isNotEmpty()) {
            tabBackStack.pop()
            tabBackStack.lastOrNull()
        } else {
            null
        }
    }

    private fun getUpperFragment(): Fragment? {
        val tabBackStack = getBackStack()
        return tabBackStack.lastOrNull()
    }

    private fun getBackStack(): Stack<Fragment> {
        return tabStacks.getOrPut(currentTabId) { Stack() }
    }
}