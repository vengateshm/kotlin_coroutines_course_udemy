package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        sendNumbers().collect {
            println("Number $it")
        }
    }
}

fun sendNumbers() = flow {
    val numbers = listOf(1, 2, 3, 4, 5)
    numbers.forEach {
        emit(it)
        delay(it * 100L)
    }
    byListAsFlow().collect {
        println("$it")
    }
    byFlowOf().collect {
        println("$it")
    }
}

// Creating flows
fun byListAsFlow() = listOf(1, 2, 3).asFlow()
fun byFlowOf() = flowOf(1, 2, 3)