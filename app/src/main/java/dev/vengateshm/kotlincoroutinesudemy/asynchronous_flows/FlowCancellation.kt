package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        withTimeoutOrNull(1000L) {
            sendInts().collect {
                println("$it")
            }
        }
    }
}

fun sendInts() = flow {
    val ints = listOf(1, 2, 3, 4, 5)
    ints.forEach {
        delay(400L)
        emit(it)
    }
}