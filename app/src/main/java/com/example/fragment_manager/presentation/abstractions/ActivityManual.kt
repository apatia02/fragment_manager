package com.example.fragment_manager.presentation.abstractions

import androidx.appcompat.app.AppCompatActivity
import com.example.fragment_manager.di.ActivityComponent

abstract class ActivityManual : AppCompatActivity() {
    abstract var activityComponent: ActivityComponent
}