package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.features.ClickCounter
import com.example.fragment_manager.presentation.abstractions.FragmentManual

class FragmentComponent(val activityComponent: ActivityComponent) {

    lateinit var clickCounter: ClickCounter

    fun inject(fragmentManual: FragmentManual) {
        fragmentManual.fragmentComponent = this
        clickCounter = ClickCounter(fragmentManual)
    }
}