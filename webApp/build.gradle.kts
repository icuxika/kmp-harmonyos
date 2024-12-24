plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "com.icuxika.kh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
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
        }
    }
}