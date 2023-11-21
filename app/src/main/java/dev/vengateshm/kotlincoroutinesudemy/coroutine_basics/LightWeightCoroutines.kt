package dev.vengateshm.kotlincoroutinesudemy.coroutine_basics

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        repeat(1_000_000) {
            launch {
                print(".")
            }
        }
    }
    println()
    println("Hello!!!")
}