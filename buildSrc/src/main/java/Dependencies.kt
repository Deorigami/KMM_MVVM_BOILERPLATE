

object Dependencies {
    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Version.ktor}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Version.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Version.ktor}"
        const val androidOkhttp = "io.ktor:ktor-client-okhttp:${Version.ktor}"
    }
    object Koin {
        const val core = "io.insert-koin:koin-core:${Version.koin}"
        const val android = "io.insert-koin:koin-android:${Version.koin}"
    }
    object Mokko {
        const val core = "dev.icerock.moko:mvvm-core:${Version.mokko}"
        const val flow = "dev.icerock.moko:mvvm-flow:${Version.mokko}"
        const val resources = "dev.icerock.moko:resources:${Version.mokkoResources}"

        object Compose {
            const val flow = "dev.icerock.moko:mvvm-flow-compose:${Version.mokko}"
            const val resource = "dev.icerock.moko:resources-compose:${Version.mokkoResources}"
        }
    }
    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    }
    object KotlinX {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinx}"
    }
    object Android {
        const val chucker = "com.github.chuckerteam.chucker:library:${Version.chucker}"
        const val ksp = "com.google.devtools.ksp:symbol-processing-api:${Version.ksp}}"
        object Compose {
            const val constraint = "androidx.constraintlayout:constraintlayout-compose:${Version.constraint}"
            const val ui = "androidx.compose.ui:ui:${Version.compose}"
            const val pager = "com.google.accompanist:accompanist-pager:${Version.pager}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Version.compose}"
            const val material = "androidx.compose.material:material:${Version.compose}"
            const val materialIconsCore = "androidx.compose.material:material-icons-core:${Version.compose}"
            const val liveData = "androidx.compose.runtime:runtime-livedata:${Version.compose}"
            const val activity = "androidx.activity:activity-compose:1.5.1"
            const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
            const val destinationsCore = "io.github.raamcosta.compose-destinations:core:${Version.navigation}"
            const val destinationsAnimations = "io.github.raamcosta.compose-destinations:animations-core:${Version.navigation}"
            const val destinationsKsp = "io.github.raamcosta.compose-destinations:ksp:${Version.navigation}"
            const val coil = "io.coil-kt:coil-compose:${Version.coil}"
        }
    }
}