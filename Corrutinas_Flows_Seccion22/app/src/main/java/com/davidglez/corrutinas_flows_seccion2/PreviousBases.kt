package com.davidglez.corrutinas_flows_seccion2

/**
 * Created by davidgonzalez on 27/03/23
 */
fun main() {
    println("Coroutines and Flows")
    lambda()
}

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
