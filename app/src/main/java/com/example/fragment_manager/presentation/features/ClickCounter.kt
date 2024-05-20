package com.example.fragment_manager.presentation.features

class ClickCounter {
    private var clickCount = 0

    fun incrementClickCount() {
        clickCount++
    }

    fun getClickCount(): Int {
        return clickCount
    }
}
