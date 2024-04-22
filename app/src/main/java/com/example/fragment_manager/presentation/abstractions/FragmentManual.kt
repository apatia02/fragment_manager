package com.example.fragment_manager.presentation.abstractions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fragment_manager.di.FragmentComponent

abstract class FragmentManual : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent = FragmentComponent((activity as ActivityManual).activityComponent)
        val configurator = createConfigurator()
        configurator.inject(this, fragmentComponent)
        init()
    }

    protected open fun init() {}

    abstract fun createConfigurator(): FragmentManualConfigurator
}