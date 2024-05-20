package com.example.fragment_manager.presentation.fragments.di

import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.fragments.Tab1Fragment

class Tab1Configurator : CommonTabConfigurator() {

    override fun inject(fragmentManual: FragmentManual, fragmentComponent: FragmentComponent) {
        super.inject(fragmentManual, fragmentComponent)
        val fragment = fragmentManual as Tab1Fragment
        fragment.clickCounter = fragmentComponent.clickCounter
    }
}