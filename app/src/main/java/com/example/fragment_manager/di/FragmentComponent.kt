package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.features.ClickCounter

class FragmentComponent(val activityComponent: ActivityComponent) {

    val clickCounter = ClickCounter()
}