/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin( "plugin.serialization" ) version ("1.7.0")
}

val dependency = listOf(
    Dependencies.Android.Compose.ui,
    Dependencies.Android.Compose.uiTooling,
    Dependencies.Android.Compose.foundation,
    Dependencies.Android.Compose.material,
    Dependencies.Android.Compose.materialIconsCore,
    Dependencies.Android.Compose.liveData,
    Dependencies.Android.Compose.activity,
    Dependencies.Android.Compose.lifecycleViewModel,
    Dependencies.Android.Compose.navigation,
    Dependencies.Android.chucker,
    Dependencies.Ktor.core,
    Dependencies.Ktor.androidOkhttp,
    Dependencies.Koin.core,
)

android {
    compileSdk = 32
    buildFeatures {
        compose = true
    }
    defaultConfig {
        applicationId = "dev.icerock.moko.mvvm.sample.declarativeui.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions.jvmTarget = "1.8"
    buildTypes {
        debug {
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
    }
}



dependencies {
    implementation(project(":shared"))
    dependency.forEach { implementation(it) }
}