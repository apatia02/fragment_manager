package com.example.fragment_manager

import android.app.Application
import com.example.fragment_manager.di.AppComponent

class App : Application() {
    val appComponent = AppComponent()
}