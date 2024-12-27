## 在`HarmonyOS`中使用如下代码时，kmp生成的`JavaScript`代码使用了许多在`HarmonyOS`中不存在的`API`
```
import { ApiExecutorJs } from 'shared/src/main/ets/kmp-harmonyos-shared'
let apiExecutorJs = new ApiExecutorJs()
apiExecutorJs.execute((body) => {
  this.message = body
})
```

### `ktor-client`在`JavaScript`端使用了`AbortController`
> 相关[@ohos-rs/abort-controller](https://github.com/ohos-rs/abort-controller)
[ktor-client/ktor-client-core/js/src/io/ktor/client/engine/js/compatibility/Utils.kt](https://github.com/ktorio/ktor/blob/main/ktor-client/ktor-client-core/js/src/io/ktor/client/engine/js/compatibility/Utils.kt)
```kotlin
internal fun AbortController(): AbortController {
    return js("new AbortController()")
}
```
当在`HarmonyOS`中引入时，在对应的`js`文件开头添加
```javascript
import emitter from "@ohos.events.emitter";

class AbortSignal {
    onabort = null;
    aborted = false;
    reason = undefined;

    constructor() {
        this.onabort = null;
        this.aborted = false;
        this.reason = undefined;
    }
    toString() {
        return "[object AbortSignal]";
    }
    removeEventListener(name, handler) {
        emitter.off(name, handler);
    }
    addEventListener(name, handler) {
        emitter.on(name, handler);
    }
    dispatchEvent(type) {
        const event = { type, target: this };
        const handlerName = `on${type}`;

        if (typeof this[handlerName] === "function") {
            this[handlerName](event);
        }

        emitter.emit(type, event);
    }
    throwIfAborted() {
        if (this.aborted) {
            throw new Error(this.reason);
        }
    }
    static abort(reason) {
        const controller = new AbortController();
        controller.abort(reason);
        return controller.signal;
    }
    static timeout(time) {
        const controller = new AbortController();
        setTimeout(() => controller.abort(new Error("TimeoutError")), time);
        return controller.signal;
    }
}

class AbortController {
    signal;
    constructor() {
        this.signal = new AbortSignal();
    }
    abort(reason) {
        if (this.signal.aborted) return;

        this.signal.aborted = true;

        if (reason) {
            this.signal.reason = reason;
        } else {
            this.signal.reason = new Error("AbortError");
        }

        this.signal.dispatchEvent("abort");
    }
    toString() {
        return "[object AbortController]";
    }
}
```

### `ktor-client`在`JavaScript`端使用了`fetch`，而`HarmonyOS`已将`@system.fetch`弃用
> `@system.fetch`与`Web API`中`Fetch API`不一致，无法直接替换
> 待研究