package com.icuxika.kh

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val greeting = createGreeting()
    println(greeting.greet())
    runBlocking {
        launch {
            val apiData = ApiExecutor().requestK<ApiData<String>>()
            println(apiData.data)
        }
    }
}