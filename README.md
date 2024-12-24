# Kotlin Multiplatform 与 Harmony OS

## 构建 Android
```
$env:ANDROID_HOME = "C:\Users\icuxika\AppData\Local\Android\Sdk\"
.\gradlew.bat :composeApp:build

adb tcpip 5555
adb connect 192.168.50.64:5555
adb install .\composeApp\build\outputs\apk\debug\composeApp-debug.apk
```

## 构建 [Shared]JavaScript
```
.\gradlew.bat :shared:compileDevelopmentExecutableKotlinJs
.\gradlew.bat :shared:compileProductionExecutableKotlinJs
```
在`Harmony OS`中引入的时候需要将文件后缀从`mjs`修改为`js`
```
cp .\shared\build\compileSync\js\main\productionExecutable\kotlin\* .\harmonyosApp\shared\src\main\ets\
mv .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.mjs .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.js
mv .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.mjs.map .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.js.map
```

## 构建 [Shared]Native
```
.\gradlew.bat :shared:linkDebugStaticNative
.\gradlew.bat :shared:linkDebugSharedNative
```