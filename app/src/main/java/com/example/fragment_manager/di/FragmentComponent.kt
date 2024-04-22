package com.example.fragment_manager.di

import com.example.fragment_manager.presentation.features.ClickCounter
import com.example.fragment_manager.presentation.features.RandomNumberGenerator

class FragmentComponent(val activityComponent: ActivityComponent) {

    val clickCounter = ClickCounter()

    val randomNumberGenerator = RandomNumberGenerator()
}