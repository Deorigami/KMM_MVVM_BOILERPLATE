plugins {
    id("com.android.library")
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
    Dependencies.Android.Compose.activity,
    Dependencies.Android.Compose.coil,
    Dependencies.Android.Compose.constraint,
    Dependencies.Android.chucker,
    Dependencies.Android.ksp,


    Dependencies.Android.Compose.destinationsAnimations,
    Dependencies.Android.Compose.destinationsCore,
    Dependencies.Android.Compose.destinationsKsp,
)

android {
    compileSdk = 32
    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions.jvmTarget = "1.8"
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
    }
    libraryVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":androidModule:core"))
    dependency.forEach {
        when {
            it.contains("ksp") -> ksp(it)
            else -> implementation(it)
        }
    }
    debugImplementation("androidx.customview:customview:1.2.0-alpha01")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")

    implementation(project(":androidModule:component"))
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "dashboard")
}