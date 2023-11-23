package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        tryCatch()
//        catch()
        onCompletion()
    }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .collect(::println)
    } catch (e: Exception) {
        println("Caught exception ${e.localizedMessage}")
    }
}

suspend fun catch() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught exception ${e.localizedMessage}") }
        .collect(::println)
}

suspend fun onCompletion() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed with $cause")
            } else {
                println("Flow completed successfully")
            }
        }
        .catch { e -> println("Caught exception ${e.localizedMessage}") }
        .collect(::println)
}