package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.presentation.features.TabFragmentNavigator

class ActivityComponent(val appComponent: AppComponent) {
    lateinit var tabFragmentNavigator: TabFragmentNavigator

    fun setTabFragmentNavigator(
        activityManual: ActivityManual,
        initialTab: String,
        fragmentContainer: Int
    ) {
        tabFragmentNavigator = TabFragmentNavigator(
            activity = activityManual,
            initialTab = initialTab,
            fragmentContainer = fragmentContainer
        )
    }
}