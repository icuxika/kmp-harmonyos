package com.icuxika.kh

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
