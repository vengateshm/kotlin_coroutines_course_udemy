package dev.vengateshm.kotlincoroutinesudemy.channel_apis

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (i in 1..5) {
                channel.send(i * i)
            }
            channel.close()
        }
        /*for (i in 1..5)
            println(channel.receive())*/
        for (i in channel) {
            println(i)
        }
    }
}
