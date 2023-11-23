package dev.vengateshm.kotlincoroutinesudemy.channel_apis

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.numbers() = produce {
    for (i in 1..5)
        send(i)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.squares(numbers: ReceiveChannel<Int>) = produce {
    for (i in numbers)
        send(i * i)
}

fun main() {
    runBlocking {
        val numbers = numbers()
        val squares = squares(numbers)
        for (i in squares) {
            println(i)
        }
        coroutineContext.cancelChildren()
    }
}