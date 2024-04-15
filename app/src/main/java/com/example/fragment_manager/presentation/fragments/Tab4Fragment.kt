package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_4_ID
import com.example.fragment_manager.databinding.FragmentTab4Binding
import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.features.TabFragmentNavigator

class Tab4Fragment : FragmentManual() {

    private lateinit var binding: FragmentTab4Binding

    override lateinit var fragmentComponent: FragmentComponent

    private lateinit var tabFragmentNavigator: TabFragmentNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab4Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ActivityManual).activityComponent.injectFragmentComponent(this)
        init()
    }

    private fun init() {
        tabFragmentNavigator = fragmentComponent.activityComponent.tabNavigator
        binding.openFunnyFragmentBtn.setOnClickListener {
            openFunnyFragment()
        }
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