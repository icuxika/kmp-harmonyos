package com.icuxika.kh

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

class ApiExecutor {
    suspend fun execute(): String {
        val response = client.get("https://www.aprillie.com/go-transfer-station/getOne")
        val stringBody: String = response.body()
        return stringBody
    }

    companion object {
        private val client = httpClient()
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport
class ApiExecutorJs {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("error--------------------")
        exception.printStackTrace()
    }

    @JsName("execute")
    fun execute(callback: (String) -> Unit) {
        scope.launch(exceptionHandler) {
            val response = client.get("https://www.aprillie.com/go-transfer-station/getOne")
            val stringBody: String = response.body()
            callback(stringBody)
        }
    }

    companion object {
        private val client = httpClient()
        private val scope = CoroutineScope(SupervisorJob())
    }
}