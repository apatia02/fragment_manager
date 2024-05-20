package com.example.fragment_manager.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentTab1Binding
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.abstractions.TabFragment
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.KEY_WORD_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_1_ID
import com.example.fragment_manager.presentation.features.ClickCounter
import com.example.fragment_manager.presentation.features.TabFragmentNavigator
import com.example.fragment_manager.presentation.fragments.di.Tab1Configurator


class Tab1Fragment : TabFragment() {

    private lateinit var binding: FragmentTab1Binding

    override lateinit var tabFragmentNavigator: TabFragmentNavigator

    lateinit var clickCounter: ClickCounter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab1Binding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun init(): Unit = with(binding) {
        openFunnyFragmentBtn.setOnClickListener {
            openFunnyFragment()
        }
        binding.root.setOnTouchListener { _, _ ->
            clickCounter.incrementClickCount()
            Log.d("CLICK COUNTER", "Tab1Fragment click count:${clickCounter.getClickCount()}")
            false
        }
    }

    override fun createConfigurator(): FragmentManualConfigurator {
        return Tab1Configurator()
    }

    private fun openFunnyFragment() {
        val bundle = Bundle().apply {
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_1_ID)
            putString(KEY_WORD_FUN_ARGUMENT, binding.somethingEt.text.toString())
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        tabFragmentNavigator.replace(funnyFragment)
    }
}