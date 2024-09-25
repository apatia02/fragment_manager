package com.example.fragment_manager.di

import android.content.Context
import com.example.fragment_manager.data.ImageRepository
import com.example.fragment_manager.data.UserShared

class AppComponent(context: Context) {

    val imageRepository by lazy { ImageRepository() }

    val userShared by lazy { UserShared(context) }
}