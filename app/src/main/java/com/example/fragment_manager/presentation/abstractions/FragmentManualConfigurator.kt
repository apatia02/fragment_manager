package com.example.fragment_manager.presentation.abstractions

import com.example.fragment_manager.di.FragmentComponent

interface FragmentManualConfigurator {

    fun inject(fragmentManual: FragmentManual, fragmentComponent: FragmentComponent)
}