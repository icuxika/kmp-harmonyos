package com.icuxika.kh

import kotlin.experimental.ExperimentalNativeApi
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.native.CName

@OptIn(ExperimentalJsExport::class)
@JsExport
class Greeting {
    private val platform = getPlatform()

    @JsName("greet")
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

@OptIn(ExperimentalNativeApi::class)
@CName("createGreeting")
fun createGreeting(): Greeting {
    return Greeting()
}