package com.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DummyTest {

    @Test
    fun `test addition`() {
        val sum = 2 + 3
        assertEquals(5, sum)
    }

    @Test
    fun `test subtraction`() {
        val difference = 5 - 3
        assertEquals(2, difference)
    }
}