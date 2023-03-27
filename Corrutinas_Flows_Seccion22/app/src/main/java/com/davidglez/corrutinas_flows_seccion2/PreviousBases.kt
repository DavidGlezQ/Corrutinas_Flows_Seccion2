package com.davidglez.corrutinas_flows_seccion2

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.random.Random

/**
 * Created by davidgonzalez on 27/03/23
 */
fun main() {
    println("Coroutines and Flows")
    //lambda()
    //threads()
    coroutinesVsThreads()
}

fun coroutinesVsThreads() {
    newTopic("Coroutines Vs Threads")
    runBlocking {
        (1..1_000_000).forEach {
            launch {
                delay(someTime())
                print("*")
            }
        }
    }
    /*(1..1_000_000).forEach {
        thread {
            Thread.sleep(someTime())
            println("*")
        }
    }*/

}

private const val SEPARATOR = "======================"
fun newTopic(topic: String) {
    println("\n$SEPARATOR $topic $SEPARATOR")
}

fun threads() {
    println("Thread, ${multiThread(2, 3)}")
    multiThreadLambda(2, 3) {
        println("Thread + Lambda, $it")
    }
}

fun multiThread(x: Int, y: Int): Int {
    var result = 0

    thread {
        Thread.sleep(someTime())
        result = x * y
    }
    Thread.sleep(2_100)
    return result
}

fun multiThreadLambda(x: Int, y: Int, callback: (result: Int) -> Unit) {
    var result = 0

    thread {
        Thread.sleep(someTime())
        result = x * y
        callback(result)
    }
}

fun someTime(): Long = Random.nextLong(500, 2_000)

fun lambda() {
    println(multi(2, 3))
    multiLambda(2, 3) {
        println("result with lambda, $it")
    }
}

fun multiLambda(x: Int, y: Int, callback: (result: Int) -> Unit) {
    callback(x * y)
}

fun multi(x: Int, y: Int): Int {
    return  x * y
}
