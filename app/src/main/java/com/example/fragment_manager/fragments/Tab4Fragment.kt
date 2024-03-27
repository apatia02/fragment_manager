package com.example.fragment_manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_manager.R
import com.example.fragment_manager.TabBackStackManager
import com.example.fragment_manager.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.constants.TAB_4_ID
import com.example.fragment_manager.databinding.FragmentTab4Binding

class Tab4Fragment : Fragment() {

    private lateinit var binding: FragmentTab4Binding

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
        init()
    }

    private fun init() {
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, funnyFragment)
            .commit()
        TabBackStackManager.pushFragment(TAB_4_ID, funnyFragment)
    }
}