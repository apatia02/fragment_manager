package com.example.fragment_manager.features

import kotlin.random.Random

class RandomNumberGenerator {

    fun generateRandomNumber(): Any {
        return Random.nextInt()
    }

    fun generateRandomNumberInRange(min: Int, max: Int): Int {
        return Random.nextInt(min, max)
    }
}