package com.icuxika.kh

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform