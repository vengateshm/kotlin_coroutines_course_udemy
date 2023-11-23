package dev.vengateshm.kotlincoroutinesudemy.channel_apis

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        val channel = produce {
            for (i in 1..5) send(i * i)
        }
        for (i in channel) println(i)
        for (i in produceSquares()) println(i)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceSquares() = produce {
    for (i in 1..5) send(i * i)
}
