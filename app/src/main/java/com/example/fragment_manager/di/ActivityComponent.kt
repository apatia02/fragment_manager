package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.presentation.features.TabFragmentNavigator

class ActivityComponent(val appComponent: AppComponent) {

    private var tabNavigator: TabFragmentNavigator? = null

    private lateinit var activityManual: ActivityManual

    fun setTabFragmentNavigator(
        initialTab: String,
        fragmentContainer: Int
    ) {
        tabNavigator = TabFragmentNavigator(
            activity = activityManual,
            initialTab = initialTab,
            fragmentContainer = fragmentContainer
        )
        activityManual.tabFragmentNavigator = tabNavigator
    }

    fun inject(activityManual: ActivityManual) {
        this.activityManual = activityManual
    }
}