package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.R
import com.example.fragment_manager.databinding.FragmentFunnyBinding
import com.example.fragment_manager.domain.ImageRepository
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.constants.KEY_INITIAL_TAB_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.KEY_WORD_FUN_ARGUMENT
import com.example.fragment_manager.presentation.constants.TAB_1_ID
import com.example.fragment_manager.presentation.constants.TAB_2_ID
import com.example.fragment_manager.presentation.constants.TAB_3_ID
import com.example.fragment_manager.presentation.constants.TAB_4_ID
import com.example.fragment_manager.presentation.fragments.di.FunnyConfigurator

class FunnyFragment : FragmentManual() {

    private lateinit var binding: FragmentFunnyBinding

    lateinit var imageRepository: ImageRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFunnyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun init() {
        val funImage = when (arguments?.getString(KEY_INITIAL_TAB_FUN_ARGUMENT).toString()) {
            TAB_1_ID -> imageRepository.getImage()
            TAB_2_ID -> R.drawable.fun_2
            TAB_3_ID -> R.drawable.fun_3
            TAB_4_ID -> R.drawable.fun_4
            else -> R.drawable.fun_1
        }
        binding.funImage.setImageResource(funImage)
        binding.receivedTextTv.text = arguments?.getString(KEY_WORD_FUN_ARGUMENT).toString()
    }

    override fun createConfigurator(): FragmentManualConfigurator {
        return FunnyConfigurator()
    }
}