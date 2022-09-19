plugins {
    id("com.android.application")
    kotlin("android")
    kotlin( "plugin.serialization" ) version (Version.kotlin)
    id("com.google.devtools.ksp") version "${Version.kotlin}-1.0.6"
}

val dependency = listOf(
    Dependencies.Android.Compose.ui,
    Dependencies.Android.Compose.uiTooling,
    Dependencies.Android.Compose.uiToolingPreview,
    Dependencies.Android.Compose.foundation,
    Dependencies.Android.Compose.material,
    Dependencies.Android.Compose.materialIconsCore,
    Dependencies.Android.Compose.liveData,
    Dependencies.Android.Compose.constraint,
    Dependencies.Android.Compose.activity,
    Dependencies.Android.Compose.lifecycleViewModel,
    Dependencies.Android.Compose.destinationsAnimations,
    Dependencies.Android.Compose.destinationsCore,
    Dependencies.Android.Compose.destinationsKsp,
    Dependencies.Android.Compose.coil,
    Dependencies.Android.chucker,
    Dependencies.Android.ksp,
    Dependencies.Ktor.core,
    Dependencies.Ktor.androidOkhttp,
    Dependencies.Koin.core,
)

android {
    compileSdk = 33
    buildFeatures {
        compose = true
    }
    defaultConfig {
        applicationId = "com.eyedea.boilerplater"
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
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(project(":shared"))
    dependency.forEach {
        if (it.contains("ksp")) ksp(it)
        else implementation(it)
    }
    implementation(project(":androidModule:component"))
    implementation(project(":androidModule:features:feature_product"))
    implementation(project(":androidModule:features:feature_dashboard"))
    implementation(project(":androidModule:features:feature_auth"))
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

}