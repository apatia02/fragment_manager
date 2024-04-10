package com.example.fragment_manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_manager.R
import com.example.fragment_manager.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.constants.TAB_1_ID
import com.example.fragment_manager.constants.TAB_2_ID
import com.example.fragment_manager.constants.TAB_3_ID
import com.example.fragment_manager.constants.TAB_4_ID
import com.example.fragment_manager.databinding.FragmentFunnyBinding

class FunnyFragment : Fragment() {

    private lateinit var binding: FragmentFunnyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunnyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val funImage = when (arguments?.getString(KEY_INITIAL_TAB_FUN_ARGUMENT).toString()) {
            TAB_1_ID -> R.drawable.fun_1
            TAB_2_ID -> R.drawable.fun_2
            TAB_3_ID -> R.drawable.fun_3
            TAB_4_ID -> R.drawable.fun_4
            else -> R.drawable.fun_1
        }
        binding.funImage.setImageResource(funImage)
    }
}