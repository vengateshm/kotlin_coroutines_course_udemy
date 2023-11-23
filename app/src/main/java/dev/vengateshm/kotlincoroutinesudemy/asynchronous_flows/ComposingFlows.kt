package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        zipOperator()
//        combineOperator()
        mergeOperator()
    }
}

suspend fun zipOperator() {
    val english = flowOf("One", "Two", "Three").onEach { delay(100) }
    val french = flowOf("Un", "Deux", "Trois").onEach { delay(2000) }
    english.zip(french) { a, b ->
        "$a in french is $b"
    }.collect {
        println(it)
    }
}

suspend fun combineOperator() {
    val numbers = (1..5).asFlow()
        .onEach { delay(300) }
    val values = flowOf("I", "II", "III", "IV", "V")
        .onEach { delay(400) }
    numbers.combine(values) { a, b ->
        "$a -> $b"
    }.collect {
        println(it)
    }
}

suspend fun mergeOperator() {

}