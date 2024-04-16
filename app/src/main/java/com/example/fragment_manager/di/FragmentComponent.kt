package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.features.ClickCounter

class FragmentComponent(val activityComponent: ActivityComponent) {

    fun inject(fragmentManual: FragmentManual) {
        fragmentManual.clickCounter = ClickCounter()
    }
}