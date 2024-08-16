package com.example.helloworld

import java.util.Scanner
import java.util.Stack

val stack = Stack<Double>()

fun evaluateRPN(tokens: List<String>): String {

    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> {
                // If token is a number, push it to the stack
                stack.push(token.toDouble())
            }

            token in arrayOf("+", "-", "*", "/") -> {
                // If token is an operator, perform the operation
                if (stack.size < 2) {
                    stack.clear()
                    return ("Error: Insufficient values in the stack.")
                }
                val b = stack.pop()
                val a = stack.pop()
                try {
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> {
                            if (b == 0.0) throw ArithmeticException("Division by zero")
                            a / b
                        }

                        else -> throw IllegalArgumentException("Unknown operator")
                    }
                    stack.push(result)
                } catch (e: ArithmeticException) {
                    stack.clear()
                    return ("Error: ${e.message}")
                } catch (e: IllegalArgumentException) {
                    stack.clear()
                    return ("Error: ${e.message}")
                }
            }
            else -> {
                stack.clear()
                return ("Error: Invalid input ")
            }

        }
    }

    if (stack.isNotEmpty()) {
        return("${stack.peek()}")
    }

    throw IllegalStateException()

}

fun main() {
        val scanner = Scanner(System.`in`)

        println("RPN Calculator. Enter 'q' to quit.")

        while (true) {
            print("> ")
            val input = try {
                scanner.nextLine()
            } catch (e: NoSuchElementException) {
                // Handle EOF (Ctrl+D)
                println("\nExiting...")
                break
            }

            if (input.trim() == "q") {
                println("Exiting...")
                break
            }


            println(evaluateRPN(input.split("\\s+".toRegex())))

        }
}

