package com.icuxika.kh

import js.reflect.unsafeCast
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
import kotlinx.serialization.json.Json
import web.http.Request
import web.http.RequestInit
import web.http.RequestMethod
import web.http.fetch

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
                                val apiData = ApiExecutor().requestK<ApiData<String>>()
                                window.alert(apiData.data ?: apiData.msg)
                            }
                        }
                    }
                    button {
                        text("fetch")
                        onClickFunction = {
                            scope.launch {
                                val json = fetch(
                                    input = Request("https://www.aprillie.com/go-transfer-station/getOne"),
                                    init = RequestInit(method = RequestMethod.GET)
                                ).json()
                                val apiData = unsafeCast<ApiData<String>>(json)
                                console.log(apiData)
                                // unsafeCast 对得到的结果进行取值并通过console.log打印会遇到奇怪错误
                                val x = Json.decodeFromString<ApiData<String>>(JSON.stringify(json))
                                window.alert(x.data ?: x.msg)
                            }
                        }
                    }
                }
            }
        }
    }
}