package dev.vengateshm.kotlincoroutinesudemy.asynchronous_flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        mapOperator()
//        filterOperator()
//        transformOperator()
//        takeOperator()
//        reduceOperator()
        flowOnOperator()
    }
}

suspend fun mapOperator() {
    (1..10).asFlow()
        .map {
            delay(500L)
            "mapping $it"
        }
        .collect(::println)
}

suspend fun filterOperator() {
    (1..10).asFlow()
        .filter {
            delay(500L)
            it % 2 == 0
        }
        .collect(::println)
}

suspend fun transformOperator() {
    (1..10).asFlow()
        .transform {
            emit("Emitting string value $it")
            emit(it)
        }
        .collect(::println)
}

suspend fun takeOperator() {
    (1..10).asFlow().take(2).collect(::println)
}

suspend fun reduceOperator() {
    val factorial = (1..10).asFlow()
        .reduce { accumulator, value -> accumulator * value }
    println("Factorial $factorial")
}

suspend fun flowOnOperator() {
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect(::println)
}