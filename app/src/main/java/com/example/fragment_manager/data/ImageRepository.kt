package com.example.fragment_manager.data

import com.example.fragment_manager.R
import com.example.fragment_manager.domain.ImageRepository

fun ImageRepository(): ImageRepository {
    return object : ImageRepository {
        override fun getImage(): Int {
            return R.drawable.fun_1
        }
    }
}