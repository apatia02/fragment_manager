package com.example.fragment_manager.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragment_manager.R
import com.example.fragment_manager.databinding.ActivityMainBinding
import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.presentation.abstractions.ActivityManualConfigurator
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_1_ID
import com.example.fragment_manager.presentation.constants.TAB_2_ID
import com.example.fragment_manager.presentation.constants.TAB_3_ID
import com.example.fragment_manager.presentation.constants.TAB_4_ID
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.fragments.FunnyFragment
import com.example.fragment_manager.presentation.fragments.Tab1Fragment
import com.example.fragment_manager.presentation.fragments.Tab2Fragment
import com.example.fragment_manager.presentation.fragments.Tab3Fragment
import com.example.fragment_manager.presentation.fragments.Tab4Fragment
import com.example.fragment_manager.presentation.notification.NotificationHelper
import com.example.fragment_manager.presentation.notification.ReplyReceiver

class MainActivity : ActivityManual() {

    private lateinit var binding: ActivityMainBinding

    lateinit var tabFragmentNavigator: TabFragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun createConfigurator(): ActivityManualConfigurator {
        return MainActivityConfigurator(TAB_1_ID, R.id.fragment_container)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun init() {
        setListeners()
        binding.bottomNavigationView.selectedItemId = R.id.tab_1
        handleIntent(intent)
    }

    private fun setListeners() = with(binding) {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tab_1 -> selectTab(TAB_1_ID)
                R.id.tab_2 -> selectTab(TAB_2_ID)
                R.id.tab_3 -> selectTab(TAB_3_ID)
                R.id.tab_4 -> selectTab(TAB_4_ID)
            }
            true
        }
    }

    private fun selectTab(tabId: String) {
        this.tabFragmentNavigator.switchTab(tabId, getFragmentByTabId(tabId))
    }

    private fun handleIntent(intent: Intent?) {
        intent?.extras?.let {
            val tab = it.getInt(NotificationHelper.TAB_ARGUMENT)
            val tabIdFromSingle = it.getString(NotificationHelper.TAB_ARGUMENT)
            val tabIdFromReceiver = it.getString(ReplyReceiver.EXTRA_REPLY)
            when {
                tabIdFromReceiver != null -> openFunFragment(tabIdFromReceiver)
                tabIdFromSingle != null -> openFunFragment(tabIdFromSingle)
                else -> binding.bottomNavigationView.selectedItemId = tab
            }

        }
    }

    private fun openFunFragment(tabId: String) = with(binding) {
        when (tabId) {
            TAB_1_ID -> bottomNavigationView.selectedItemId = R.id.tab_1
            TAB_2_ID -> bottomNavigationView.selectedItemId = R.id.tab_2
            TAB_3_ID -> bottomNavigationView.selectedItemId = R.id.tab_3
            else -> bottomNavigationView.selectedItemId = R.id.tab_4
        }
        val bundle = Bundle().apply {
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, tabId)
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        tabFragmentNavigator.replace(funnyFragment)
    }

    private fun getFragmentByTabId(tabId: String): Fragment {
        return when (tabId) {
            TAB_1_ID -> Tab1Fragment()
            TAB_2_ID -> Tab2Fragment()
            TAB_3_ID -> Tab3Fragment()
            TAB_4_ID -> Tab4Fragment()
            else -> Tab1Fragment()
        }
    }

    override fun onBackPressed() {
        this.tabFragmentNavigator.popBackStack()
    }
}