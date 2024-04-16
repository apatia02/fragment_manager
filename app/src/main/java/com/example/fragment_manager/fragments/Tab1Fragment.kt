package com.example.fragment_manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_manager.MainActivity
import com.example.fragment_manager.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.constants.KEY_WORD_FUN_ARGUMENT
import com.example.fragment_manager.constants.TAB_1_ID
import com.example.fragment_manager.databinding.FragmentTab1Binding


class Tab1Fragment : Fragment() {

    private lateinit var binding: FragmentTab1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.openFunnyFragmentBtn.setOnClickListener {
            openFunnyFragment()
        }
    }

    private fun openFunnyFragment() {
        val bundle = Bundle().apply {
            putString(KEY_INITIAL_TAB_FUN_ARGUMENT, TAB_1_ID)
            putString(KEY_WORD_FUN_ARGUMENT, binding.somethingEt.text.toString())
        }
        val funnyFragment = FunnyFragment()
        funnyFragment.arguments = bundle
        MainActivity.tabFragmentNavigator?.replace(funnyFragment)
    }
}