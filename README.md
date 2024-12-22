# Kotlin Multiplatform 与 Harmony OS

## 构建 Android
```
$env:ANDROID_HOME = "C:\Users\icuxika\AppData\Local\Android\Sdk\"
.\gradlew.bat build
```

## 构建 JavaScript
```
.\gradlew.bat compileProductionExecutableKotlinJs
```
在`Harmony OS`中引入的时候需要将文件后缀从`mjs`修改为`js`