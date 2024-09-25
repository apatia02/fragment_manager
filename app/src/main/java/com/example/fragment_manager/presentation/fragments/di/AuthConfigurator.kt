package com.example.fragment_manager.presentation.fragments.di

import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.fragments.AuthFragment

class AuthConfigurator : FragmentManualConfigurator {

    override fun inject(fragmentManual: FragmentManual, fragmentComponent: FragmentComponent) {
        val fragment = fragmentManual as AuthFragment
        fragment.userShared = fragmentComponent.activityComponent.appComponent.userShared
    }
}