package dev.vengateshm.kotlincoroutinesudemy.concurrency_shared_state

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main() {
    runBlocking {
        var normalCounter = 0
        val atomicCounter = AtomicInteger(0)
        var fineGrainedThreadContextCounter = 0
        var mutexCounter = 0
        val mutex = Mutex()
        withContext(Dispatchers.Default) {
            // Inconsistent
            counterAction("INCONSISTENT") {
                normalCounter++
            }
            // Atomic
            counterAction("ATOMIC") {
                atomicCounter.incrementAndGet()
            }
            // Fine grained
            val counterContext = newSingleThreadContext("SingleThread")
            counterSuspendAction("FINE") {
                withContext(counterContext) {
                    fineGrainedThreadContextCounter++
                }
            }
            // Mutex
            counterSuspendAction("MUTEX") {
                mutex.withLock {
                    mutexCounter++
                }
            }
        }
        println("Normal counter $normalCounter")
        println("Atomic counter $atomicCounter")
        println("Fine grained thread context counter $fineGrainedThreadContextCounter")
        println("Mutex counter $mutexCounter")

        // Coarse grained
        val counterContext = newSingleThreadContext("SingleThread")
        var coarseGrainedThreadContextCounter = 0
        counterSuspendAction("COARSE") {
            withContext(counterContext) {
                coarseGrainedThreadContextCounter++
            }
        }
        println("Coarse grained thread context counter $coarseGrainedThreadContextCounter")
    }
}

suspend fun counterAction(type: String, action: () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("$type completed ${n * k} actions in $time ms")
}

suspend fun counterSuspendAction(type: String, action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("$type completed ${n * k} actions in $time ms")
}