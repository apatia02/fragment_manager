package com.example.fragment_manager.presentation.abstractions

import androidx.fragment.app.Fragment
import com.example.fragment_manager.di.FragmentComponent

abstract class FragmentManual : Fragment() {
    abstract var fragmentComponent: FragmentComponent
}