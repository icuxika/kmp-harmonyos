package com.icuxika.kh

import io.ktor.client.*
import io.ktor.client.engine.js.*

class JsPlatform : Platform {
    override val name: String
        get() = "Js".also {
            HiLog.info(0, "testTag", "Kotlin call HiLog: %{public}s World %{public}d", arrayOf("Hello", 21))
        }
}

@JsModule("@ohos.hilog")
external class HiLog {
    companion object {
        fun debug(domain: Number, tag: String, format: String, args: Array<Any>)
        fun info(domain: Number, tag: String, format: String, args: Array<Any>)
    }
}

actual fun getPlatform(): Platform = JsPlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Js) {
    config(this)
}
