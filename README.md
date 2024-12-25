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

## 构建 iOS
> 一般通过Xcode构建部署，以下命令目前只是记录
```
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -sdk iphoneos OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED=YES clean build
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Release -sdk iphoneos OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED=YES clean build

xcodebuild -project iosApp/iosApp.xcodeproj -showBuildSettings
xcodebuild -showsdks

xcrun xctrace list devices
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -sdk iphoneos -destination "generic/platform=iOS,id=981c7d6492f3dd616c9be040216ec702b8e99bca" OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED=YES clean build 
sudo xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -sdk iphoneos -destination "generic/platform=iOS,id=981c7d6492f3dd616c9be040216ec702b8e99bca" OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED=YES install
xcrun simctl install 981c7d6492f3dd616c9be040216ec702b8e99bca ~/Library/Developer/Xcode/DerivedData/iosApp-apdoiclkrqqmcweiwrxfndyfvkkk/Build/Products/Debug-iphoneos/kmp-harmonyos.app
```