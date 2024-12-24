package com.icuxika.kh

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.typeOf

class ApiExecutor {
    suspend fun execute(): String {
        val response = client.get("https://www.aprillie.com/go-transfer-station/getOne")
        val stringBody: String = response.body()
        return stringBody
    }

    suspend inline fun <reified T : Any> requestK(url: String = "https://www.aprillie.com/go-transfer-station/getOne"): T {
        val serializer = serializer(typeOf<T>())
        return executeK(serializer, url)
    }

    suspend fun <T> executeK(serializer: KSerializer<Any?>, url: String): T {
        val response = client.get(url)
        val stringBody: String = response.body()
        @Suppress("UNCHECKED_CAST")
        return Json.decodeFromString(serializer, stringBody) as T
    }

    companion object {
        private val client = httpClient()
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ApiData<T>(
    @EncodeDefault
    var code: Int = 10000,
    @EncodeDefault
    var msg: String = "后端未返回",
    var data: T? = null
)

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