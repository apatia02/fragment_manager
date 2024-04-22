package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentTab2Binding
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.abstractions.TabFragment
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_2_ID
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.fragments.di.CommonTabConfigurator

class Tab2Fragment : TabFragment() {

    private lateinit var binding: FragmentTab2Binding

    override lateinit var tabFragmentNavigator: TabFragmentNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun init() {
        binding.openFunnyFragmentBtn.setOnClickListener {
            openFunnyFragment()
        }
    }

    override fun createConfigurator(): FragmentManualConfigurator {
        return CommonTabConfigurator()
    }

    private fun openFunnyFragment() {
        val bundle = Bundle().apply {
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_2_ID)
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        tabFragmentNavigator.replace(funnyFragment)
    }
}