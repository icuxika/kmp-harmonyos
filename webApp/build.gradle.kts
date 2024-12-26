plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
}

group = "com.icuxika.kh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        compilerOptions {
            target = "es2015"
        }

        useEsModules()
        browser()
        nodejs()
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(projects.shared)
            implementation("org.jetbrains.kotlinx:kotlinx-html:0.11.0")
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(kotlinWrappers.browser)
            implementation(kotlinWrappers.web)
        }
    }
}