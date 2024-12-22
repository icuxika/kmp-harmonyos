# Kotlin Multiplatform 与 Harmony OS

## 构建 Android
```
$env:ANDROID_HOME = "C:\Users\icuxika\AppData\Local\Android\Sdk\"
.\gradlew.bat :composeApp:build
```

## 构建 JavaScript
```
.\gradlew.bat :shared:compileProductionExecutableKotlinJs
```
在`Harmony OS`中引入的时候需要将文件后缀从`mjs`修改为`js`
```
cp .\shared\build\compileSync\js\main\productionExecutable\kotlin\* .\harmonyosApp\shared\src\main\ets\
mv .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.mjs .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.js
mv .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.mjs.map .\harmonyosApp\shared\src\main\ets\kmp-harmonyos-shared.js.map
```

## 构建 Native
```
.\gradlew.bat :nativeApp:build
```