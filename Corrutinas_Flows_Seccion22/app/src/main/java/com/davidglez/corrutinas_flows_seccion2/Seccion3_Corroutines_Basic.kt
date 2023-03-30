package com.davidglez.corrutinas_flows_seccion2

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/**
 * Created by davidgonzalez on 30/03/23
 */

fun main() {
    //globalScope()
    //suspendFun()
    newTopic("Constructores de corrutinas")
    //cRunBlocking()
    //cLaunch()
    //cAsync()
    //job()
    //deferred()
    produce()
    readLine()
}

fun produce() = runBlocking {
    newTopic("Produce")
    val names = produceNames()
    names.consumeEach { println(it) }
}

fun CoroutineScope.produceNames(): ReceiveChannel<String> = produce {
    (1..5).forEach { send("name$it")}
}

fun deferred() {
    runBlocking {
        newTopic("Deferred")
        val deferred = async {
            startMsg()
            delay(2_100)
            println("Deferred...")
            endMsg()
            multi(5, 2)
        }
        println("Deferred, $deferred")
        println("Valor del Deferred.await: ${deferred.await()}")

        val result = async {
            multi(3, 6)
        }.await()
        println("Result, $result")
    }
    /* Nota: siempre se va a mostrar el ultimo valor de la corrutina, si queremos la devolucion de
    un valor este es el constructor ideal */
}

/* Job es el ciclo de vida de una corrutina, es un trabajo en segundo plano que puede ser cancelable  */
fun job() {
    runBlocking {
        newTopic("Topic")
        val job = launch {
            startMsg()
            delay(2_100)
            println("Job...")
            endMsg()
        }
        println("Job, $job")

        //delay(4_000)
        println("isActive, ${job.isActive}")
        println("isCancelled, ${job.isCancelled}")
        println("isCompleted, ${job.isCompleted}")

        delay(someTime())
        println("Tarea cancelada o interrumpida")
        job.cancel()

        println("isActive, ${job.isActive}")
        println("isCancelled, ${job.isCancelled}")
        println("isCompleted, ${job.isCompleted}")
    }
}

fun cAsync() {
    runBlocking {
    newTopic("Async")
        val result = async {
            startMsg()
            delay(someTime())
            println("Async...")
            endMsg()
            777
        }
        println("Result, ${result.await()}")
    }
}

/* Hacer tareas que no necesiten devolver un valor o un resultado */
fun cLaunch() {
    runBlocking {
    newTopic("Launch")
        launch {
            startMsg()
            delay(someTime())
            println("Launch...")
            endMsg()
        }
    }
}

fun cRunBlocking() {
    newTopic("RunBlocking")
    runBlocking {
        startMsg()
        delay(someTime())
        println("RunBlocking...")
        endMsg()
    }
}

fun suspendFun() {
    newTopic("Suspend")
    Thread.sleep(someTime())
    //delay(someTime())
    GlobalScope.launch {
        delay(someTime())
    }
}

/* GlobalScope nos permite que cada corrutina este en ejecucion mientras la aplicacion este viva,
* es decir mientras no finalice el proceso, el launch crea una corrutina de tipo Job. */
fun globalScope() {
    newTopic("Global Scope")
    GlobalScope.launch {
        startMsg()
        delay(someTime())
        println("Mi Corrutina")
        endMsg()
    }
}

fun startMsg() {
    println("Comenzando corrutina ${Thread.currentThread().name}.")
}

fun endMsg() {
    println("Corrutina ${Thread.currentThread().name} finalizada.")
}

