import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                // Needed only for preview.
                implementation(compose.preview)
                implementation("io.ktor:ktor-client-core:2.1.2")
                implementation("io.ktor:ktor-client-cio:2.1.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        named("androidMain") {
            dependencies {
                api("androidx.appcompat:appcompat:1.3.1")
                api("androidx.core:core-ktx:1.6.0")
            }
        }
    }
}

android {
    compileSdkVersion(31)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}