package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentTab3Binding
import com.example.fragment_manager.presentation.abstractions.ActivityManual
import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_3_ID

class Tab3Fragment : FragmentManual() {

    private lateinit var binding: FragmentTab3Binding

    override lateinit var fragmentComponent: FragmentComponent

    private lateinit var tabFragmentNavigator: TabFragmentNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab3Binding.inflate(layoutInflater)
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
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_3_ID)
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        tabFragmentNavigator.replace(funnyFragment)
    }
}