package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect {
                delay(300)
                println(it)
            }
        }
        println("Collected in time $time ms")
    }
}

fun generate() = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}