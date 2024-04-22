package com.example.fragment_manager.presentation.abstractions

import com.example.fragment_manager.presentation.features.TabFragmentNavigator

abstract class TabFragment : FragmentManual() {
    abstract var tabFragmentNavigator: TabFragmentNavigator
}