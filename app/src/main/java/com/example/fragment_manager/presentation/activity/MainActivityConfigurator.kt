package com.example.fragment_manager.presentation.activity

import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.presentation.abstractions.ActivityManualConfigurator

class MainActivityConfigurator(
    private val initialTab: String,
    private val fragmentContainer: Int
) : ActivityManualConfigurator {


    override fun inject(activityManual: ActivityManual) {
        val activity = activityManual as MainActivity
        activity.activityComponent.setTabFragmentNavigator(
            activity,
            initialTab,
            fragmentContainer
        )
        activity.tabFragmentNavigator = activity.activityComponent.tabFragmentNavigator
    }
}