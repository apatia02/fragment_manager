package com.example.fragment_manager.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentTab1Binding
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_1_ID


class Tab1Fragment : FragmentManual() {

    private lateinit var binding: FragmentTab1Binding

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
        this@Tab1Fragment.view?.setOnTouchListener { _, _ ->
            clickCounter.incrementClickCount()
            Log.d("CLICK COUNTER", "Tab1Fragment click count:${clickCounter.getClickCount()}")
            false
        }
    }

    private fun openFunnyFragment() {
        val bundle = Bundle().apply {
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_1_ID)
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        activityManual.tabFragmentNavigator?.replace(funnyFragment)
    }
}