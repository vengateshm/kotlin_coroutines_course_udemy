package dev.vengateshm.kotlincoroutinesudemy.channel_apis

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val producer = generateNumbers()
        repeat(5) {
            launchProcessor(it, producer)
        }
        delay(1000L)
        producer.cancel()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.generateNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) {
    launch {
        for (i in channel) {
            println("Processor #$id received $i")
        }
    }
}