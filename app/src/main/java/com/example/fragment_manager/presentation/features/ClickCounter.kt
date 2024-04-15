package com.example.fragment_manager.presentation.features

import android.annotation.SuppressLint
import android.util.Log
import com.example.fragment_manager.presentation.abstractions.FragmentManual

@SuppressLint("ClickableViewAccessibility")
class ClickCounter(private val fragmentManual: FragmentManual) {
    private var clickCount = 0

    init {
        fragmentManual.view?.setOnTouchListener { _, _ ->
            incrementClickCount()
            false
        }
    }

    private fun incrementClickCount() {
        clickCount++
        Log.d("CLICK COUNTER", "Fragment: ${fragmentManual::class.java} \n click count:$clickCount")
    }

    fun getClickCount(): Int {
        return clickCount
    }
}
