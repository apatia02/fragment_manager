package com.example.fragment_manager.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_manager.databinding.FragmentAuthBinding
import com.example.fragment_manager.domain.UserShared
import com.example.fragment_manager.presentation.abstractions.FragmentManual
import com.example.fragment_manager.presentation.abstractions.FragmentManualConfigurator
import com.example.fragment_manager.presentation.fragments.di.AuthConfigurator
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback

class AuthFragment : FragmentManual() {

    private lateinit var binding: FragmentAuthBinding

    lateinit var userShared: UserShared

    private val vkAuthCallback = object : VKIDAuthCallback {

        override fun onAuth(accessToken: AccessToken) {
            userShared.saveUserName(accessToken.userData.firstName)
            requireActivity().recreate()
        }

        override fun onFail(fail: VKIDAuthFail) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun createConfigurator(): FragmentManualConfigurator {
        return AuthConfigurator()
    }

    override fun init() {
        setListeners()
    }

    private fun setListeners() = with(binding) {
        authTv.setOnClickListener {
            VKID.instance.authorize(requireActivity(), vkAuthCallback)
        }
    }
}