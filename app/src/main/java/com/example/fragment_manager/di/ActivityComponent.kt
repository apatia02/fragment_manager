package com.example.fragment_manager.di

import androidx.appcompat.app.AppCompatActivity
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.presentation.abstractions.FragmentManual

class ActivityComponent(val appComponent: AppComponent) {

    lateinit var tabNavigator: TabFragmentNavigator

    private val fragmentComponent = FragmentComponent(this)

    fun setTabFragmentNavigator(
        activity: AppCompatActivity,
        initialTab: String,
        fragmentContainer: Int
    ) {
        tabNavigator = TabFragmentNavigator(
            activity = activity,
            initialTab = initialTab,
            fragmentContainer = fragmentContainer
        )
    }

    fun inject(activity: ActivityManual) {
        activity.activityComponent = this
    }

    fun injectFragmentComponent(fragmentManual: FragmentManual) {
        fragmentComponent.inject(fragmentManual)
    }
}