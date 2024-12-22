import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    // https://juejin.cn/post/7379059228105621556
    // https://juejin.cn/post/7444562650917142528
    // .\gradlew.bat compileDevelopmentExecutableKotlinJs
    // .\gradlew.bat compileProductionExecutableKotlinJs
    js(IR) {
        // https://kotlinlang.org/docs/js-ir-compiler.html
        useEsModules()
        browser { }
        nodejs()
        binaries.executable()
        generateTypeScriptDefinitions()
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }

        jsMain.dependencies {
            // put your Multiplatform dependencies here
        }
    }
}

android {
    namespace = "com.icuxika.kh.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
