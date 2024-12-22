package com.icuxika.kh

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@OptIn(ExperimentalJsExport::class)
@JsExport
class Greeting {
    private val platform = getPlatform()

    @JsName("greet")
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}