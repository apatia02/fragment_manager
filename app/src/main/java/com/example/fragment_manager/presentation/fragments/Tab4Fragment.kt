package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentTab4Binding
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.abstractions.TabFragment
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_4_ID
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.fragments.di.CommonTabConfigurator

class Tab4Fragment : TabFragment() {

    private lateinit var binding: FragmentTab4Binding

    override lateinit var tabFragmentNavigator: TabFragmentNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab4Binding.inflate(layoutInflater)
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
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_4_ID)
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        tabFragmentNavigator.replace(funnyFragment)
    }
}