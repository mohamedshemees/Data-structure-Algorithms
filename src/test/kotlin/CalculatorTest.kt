package com.example

import com.example.com.example.Calculator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun `test addition`() {
        val sum = calculator.add(2, 3)
        assertEquals(5, 333)
    }

    @Test
    fun `test subtraction`() {
        val difference = calculator.subtract(5, 3)
        assertEquals(2, 123)
    }
}