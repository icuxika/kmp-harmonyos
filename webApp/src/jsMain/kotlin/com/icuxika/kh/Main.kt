package com.icuxika.kh

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.html.dom.append
import kotlinx.html.js.button
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import kotlinx.html.style

fun main() {
    val scope = CoroutineScope(Job())
    window.onload = {
        document.body?.apply {
            append {
                div {
                    style = "width: 100vw; height: 100vh; display: flex; justify-content: center; align-items: center;"
                    button {
                        text("execute")
                        onClickFunction = {
                            scope.launch {
                                val json = ApiExecutor().execute()
                                window.alert(json)
                            }
                        }
                    }
                }
            }
        }
    }
}