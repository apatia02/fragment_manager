package com.example.fragment_manager

import androidx.fragment.app.Fragment
import java.util.Stack

object TabBackStackManager {

    private val tabStacks: MutableMap<String, Stack<Fragment>> = mutableMapOf()

    private fun getBackStack(tabId: String): Stack<Fragment> {
        return tabStacks.getOrPut(tabId) { Stack() }
    }

    fun pushFragment(tabId: String, fragment: Fragment) {
        val tabBackStack = getBackStack(tabId)
        tabBackStack.push(fragment)
    }

    fun popFragment(tabId: String): Fragment? {
        val tabBackStack = getBackStack(tabId)
        return if (tabBackStack.isNotEmpty()) {
            tabBackStack.pop()
            tabBackStack.lastOrNull()
        } else {
            null
        }
    }

    fun getUpperFragment(tabId: String): Fragment? {
        val tabBackStack = getBackStack(tabId)
        return tabBackStack.lastOrNull()
    }
}