package com.icuxika.kh

import io.ktor.client.*
import io.ktor.client.engine.curl.*
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
class NativePlatform : Platform {
    override val name: String = when (kotlin.native.Platform.osFamily) {
        OsFamily.WINDOWS -> OsFamily.WINDOWS.name
        OsFamily.MACOSX -> OsFamily.MACOSX.name
        OsFamily.LINUX -> OsFamily.LINUX.name
        else -> "Native"
    }
}

actual fun getPlatform(): Platform = NativePlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Curl) {
    config(this)
    engine {
        sslVerify = false
    }
}
