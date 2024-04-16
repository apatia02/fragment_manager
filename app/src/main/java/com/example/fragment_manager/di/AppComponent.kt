package com.example.fragment_manager.di

import com.example.fragment_manager.data.ImageRepository

class AppComponent {

    val imageRepository by lazy { ImageRepository() }
}