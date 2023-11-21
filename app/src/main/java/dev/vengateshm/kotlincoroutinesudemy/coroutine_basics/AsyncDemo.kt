package dev.vengateshm.kotlincoroutinesudemy.coroutine_basics

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        val firstDeferred = async { getFirstValue() }
        val secondDeferred = async { getSecondValue() }
        println("Doing some processing here")
        delay(500L)
        println("Waiting for values")
        val first = firstDeferred.await()
        val second = secondDeferred.await()
        println("The sum of two values ${first + second}")
    }
}

suspend fun getFirstValue(): Int {
    delay(100L)
    val value = Random.nextInt(1000)
    println("Returning first value $value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(200L)
    val value = Random.nextInt(1000)
    println("Returning second value $value")
    return value
}