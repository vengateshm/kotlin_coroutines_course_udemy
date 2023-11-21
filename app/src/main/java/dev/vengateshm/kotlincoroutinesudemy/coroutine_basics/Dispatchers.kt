package dev.vengateshm.kotlincoroutinesudemy.coroutine_basics

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        /*launch(Dispatchers.Main) {
            println("Main. Thread ${Thread.currentThread().name}")
        }*/
        launch(Dispatchers.Unconfined) {
            println("Unconfined start. Thread ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined end. Thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default. Thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO. Thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext. Thread ${Thread.currentThread().name}")
        }
    }
}