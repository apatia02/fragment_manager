package com.example.fragment_manager.presentation.fragments.di

import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.fragments.FunnyFragment

class FunnyConfigurator : FragmentManualConfigurator {

    override fun inject(fragmentManual: FragmentManual, fragmentComponent: FragmentComponent) {
        val fragment = fragmentManual as FunnyFragment
        fragment.imageRepository =
            fragmentComponent.activityComponent.appComponent.imageRepository
    }
}