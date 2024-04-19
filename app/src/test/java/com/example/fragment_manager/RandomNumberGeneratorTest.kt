package com.example.fragment_manager

import com.example.fragment_manager.features.RandomNumberGenerator
import io.mockk.spyk
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class RandomNumberGeneratorTest {

    private lateinit var randomNumberGenerator: RandomNumberGenerator

    @Before
    fun setUp() {
        randomNumberGenerator = spyk()
    }

    @Test
    fun testGenerateRandomNumber() {
        val randomNumber = randomNumberGenerator.generateRandomNumber()
        assertTrue(randomNumber is Int)
    }

    @Test
    fun testGenerateRandomNumberInRange() {
        val randomNumber = randomNumberGenerator.generateRandomNumberInRange(MIN, MAX)
        assertTrue(randomNumber in MIN..MAX)
    }

    private companion object {
        const val MIN = 10
        const val MAX = 20
    }
}