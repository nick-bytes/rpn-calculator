package com.example.helloworld

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class RPNCalculatorTest {

    @Test
    fun `test addition`() {
        val result = evaluateRPN(listOf("3","4","+"))
        assertEquals(7.0.toString(), result, "3 + 4 should be 7")
    }

    @Test
    fun `test complex expression`() {
        val result = evaluateRPN(listOf("5", "1", "2", "+", "4","*", "+", "3", "-"))
        assertEquals(14.0.toString(), result, "5 + (1 + 2) * 4 - 3 should be 14")
    }

    @Test
    fun `test division`() {
        val result = evaluateRPN(listOf("10", "2", "/"))
        assertEquals(5.0.toString(), result, "10 / 2 should be 5")
    }

    @Test
    fun `test subtraction`() {
        val result = evaluateRPN(listOf("7", "3", "-"))
        assertEquals(4.0.toString(), result, "7 - 3 should be 4")
    }

    @Test
    fun `test invalid expression`() {
        assertNull(evaluateRPN(listOf("5", "1", "+", "+")).toDoubleOrNull())
    }
}
