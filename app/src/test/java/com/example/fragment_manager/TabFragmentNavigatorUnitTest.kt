package com.example.fragment_manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragment_manager.features.TabFragmentNavigator
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class TabFragmentNavigatorUnitTest {

    private lateinit var navigator: TabFragmentNavigator
    private lateinit var mockActivity: MainActivity
    private lateinit var mockFragmentManager: FragmentManager
    private lateinit var mockFragmentTransaction: FragmentTransaction

    @Before
    fun setUp() {
        mockActivity = mockk()
        mockFragmentManager = mockk()
        mockFragmentTransaction = mockk()

        every { mockActivity.supportFragmentManager } returns mockFragmentManager
        every { mockFragmentManager.beginTransaction() } returns mockFragmentTransaction
        every { mockFragmentTransaction.replace(any(), any()) } returns mockFragmentTransaction
        every { mockFragmentTransaction.commit() } returns 1

        navigator = TabFragmentNavigator(mockActivity, INITIAL_TAB, FRAGMENT_CONTAINER)
    }

    @Test
    fun switchTab_Should_Replace_Fragment() {
        val mockFragment = mockk<Fragment>()
        navigator.switchTab(NEW_TAB, mockFragment)
        verify { mockFragmentTransaction.replace(any(), mockFragment) }
    }

    @Test
    fun `popBackStack should replace fragment`() {
        val mockFragment1 = mockk<Fragment>()
        val mockFragment2 = mockk<Fragment>()
        navigator.switchTab(INITIAL_TAB, mockFragment1)
        navigator.replace(mockFragment2)

        navigator.popBackStack()

        verify { mockFragmentManager.beginTransaction() }
        verify { mockFragmentTransaction.replace(any(), mockFragment1) }
        verify { mockFragmentTransaction.replace(any(), mockFragment2) }
        verify { mockFragmentTransaction.commit() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private companion object {
        const val INITIAL_TAB = "initial_tab"
        const val NEW_TAB = "new_tab"
        const val FRAGMENT_CONTAINER = 1
    }
}