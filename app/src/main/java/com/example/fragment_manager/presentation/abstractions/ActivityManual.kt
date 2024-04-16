package com.example.fragment_manager.presentation.abstractions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment_manager.App
import com.example.fragment_manager.di.ActivityComponent
import com.example.fragment_manager.presentation.features.TabFragmentNavigator

abstract class ActivityManual : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent
    var tabFragmentNavigator: TabFragmentNavigator? = null

    fun initTabFragmentNavigator(
        initialTab: String,
        fragmentContainer: Int
    ) {
        activityComponent.setTabFragmentNavigator(
            initialTab = initialTab,
            fragmentContainer = fragmentContainer
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = ActivityComponent((application as App).appComponent)
        activityComponent.inject(this)
    }
}