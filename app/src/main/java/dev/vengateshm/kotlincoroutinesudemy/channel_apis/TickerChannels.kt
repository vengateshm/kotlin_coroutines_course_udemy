package dev.vengateshm.kotlincoroutinesudemy.channel_apis

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ObsoleteCoroutinesApi::class)
fun main() {
    runBlocking {
        val ticker = ticker(delayMillis = 100L)
        launch {
            val startTime = System.currentTimeMillis()
            ticker.consumeEach {
                val delta = System.currentTimeMillis() - startTime
                println("Received tick after delta $delta")
            }
        }
        delay(1000L)
        println("Done!")
        ticker.cancel()
    }
}