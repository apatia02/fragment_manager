package com.example.fragment_manager.di

import com.example.fragment_manager.data.ImageRepository
import com.example.fragment_manager.presentation.abstractions.ActivityManual

class AppComponent {

    val imageRepository by lazy { ImageRepository() }

    private val activityComponent = ActivityComponent(this)
    fun injectActivityComponent(activityManual: ActivityManual){
        activityComponent.inject(activityManual)
    }
}