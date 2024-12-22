package com.icuxika.kh

class JsPlatform : Platform {
    override val name: String = "Js"
}

actual fun getPlatform(): Platform = JsPlatform()
