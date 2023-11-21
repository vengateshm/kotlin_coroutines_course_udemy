package dev.vengateshm.kotlincoroutinesudemy.coroutine_basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Program execution will block now")
    runBlocking {
        launch {
            delay(1000L)
            println("Task from runBlocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("Task from GlobalScope")
        }

        coroutineScope {
            launch {
                delay(500L)
                println("Task from coroutineScope")
            }
        }
    }
    println("Program execution will continue")
}