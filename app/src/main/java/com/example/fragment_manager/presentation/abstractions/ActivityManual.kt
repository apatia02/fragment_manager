package com.example.fragment_manager.presentation.abstractions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragment_manager.App
import com.example.fragment_manager.di.ActivityComponent

abstract class ActivityManual : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = ActivityComponent((application as App).appComponent)
        val configurator = createConfigurator()
        configurator.inject(this)
    }

    abstract fun createConfigurator(): ActivityManualConfigurator
}