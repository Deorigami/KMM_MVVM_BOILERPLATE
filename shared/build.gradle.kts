/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFrameworkTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.moko.kswift")
    kotlin( "plugin.serialization" ) version ("1.7.0")
}

val mokoResourcesVersion = "0.19.0"
val mokoMvvmVersion = "0.13.0"
val ktorVersion = "2.0.3"
val moshiVersion = "1.13.0"
val koinVersion = "3.2.0"
val dependenciesList = listOf(
    "dev.icerock.moko:mvvm-core:$mokoMvvmVersion",
    "dev.icerock.moko:mvvm-flow:$mokoMvvmVersion",
    "dev.icerock.moko:resources:$mokoResourcesVersion",
    // Ktor
    "io.ktor:ktor-client-core:$ktorVersion",
    "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion",
    "io.ktor:ktor-client-content-negotiation:$ktorVersion",
    // KOIN
    "io.insert-koin:koin-core:$koinVersion",
)

kotlin {
    android()

    val xcf = XCFramework("MultiPlatformLibrary")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = "MultiPlatformLibrary"

            xcf.add(this)

            dependenciesList.forEach { export(it) }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                dependenciesList.forEach { api(it) }
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("dev.icerock.moko:mvvm-flow-compose:$mokoMvvmVersion")
                api("dev.icerock.moko:resources-compose:$mokoResourcesVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.insert-koin:koin-android:$koinVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("com.github.chuckerteam.chucker:library:3.5.2")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

afterEvaluate {
    val xcodeDir = File(project.buildDir, "xcode")

    tasks.filterIsInstance<XCFrameworkTask>()
        .forEach { xcFrameworkTask ->
            val syncName: String = xcFrameworkTask.name.replace("assemble", "sync")
            val xcframeworkDir =
                File(xcFrameworkTask.outputDir, xcFrameworkTask.buildType.getName())

            tasks.create(syncName, Sync::class) {
                this.group = "xcode"

                this.from(xcframeworkDir)
                this.into(xcodeDir)

                this.dependsOn(xcFrameworkTask)
            }
        }

    tasks.filterIsInstance<XCFrameworkTask>()
        .forEach { xcFrameworkTask ->
            val frameworkDir: File = xcFrameworkTask.outputDir
            val swiftGenDir = File(frameworkDir.parent, frameworkDir.nameWithoutExtension + "Swift")
            val xcframeworkDir =
                File(xcFrameworkTask.outputDir, xcFrameworkTask.buildType.getName())
            val targetDir = File(xcframeworkDir, swiftGenDir.name)

            @Suppress("ObjectLiteralToLambda")
            xcFrameworkTask.doLast(object : Action<Task> {
                override fun execute(t: Task) {
                    targetDir.mkdirs()
                    swiftGenDir.copyRecursively(targetDir, overwrite = true)
                }
            })
        }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
    install(dev.icerock.moko.kswift.plugin.feature.PlatformExtensionFunctionsFeature)
}
