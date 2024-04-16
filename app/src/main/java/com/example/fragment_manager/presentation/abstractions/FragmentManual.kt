package com.example.fragment_manager.presentation.abstractions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fragment_manager.di.FragmentComponent
import com.example.fragment_manager.presentation.features.ClickCounter

abstract class FragmentManual : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    lateinit var clickCounter: ClickCounter

    lateinit var activityManual: ActivityManual

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityManual = activity as ActivityManual
        fragmentComponent = FragmentComponent(activityManual.activityComponent)
        fragmentComponent.inject(this)
        init()
    }

    protected open fun init() {}
}