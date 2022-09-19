import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFrameworkTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.moko.kswift")
    kotlin( "plugin.serialization" ) version ("1.7.0")
}

val commonDependecies = listOf(
    Dependencies.Koin.core,

    Dependencies.Ktor.core,
    Dependencies.Ktor.serialization,
    Dependencies.Ktor.contentNegotiation,

    Dependencies.Mokko.core,
    Dependencies.Mokko.flow,
    Dependencies.Mokko.resources,

    Dependencies.Coroutines.core,

    Dependencies.KotlinX.serialization
)
val androidDependencies = listOf(
    Dependencies.Ktor.androidOkhttp,

    Dependencies.Koin.android,
    Dependencies.Koin.core,

    Dependencies.Mokko.Compose.flow,
    Dependencies.Mokko.Compose.resource,

    Dependencies.Android.chucker,
    Dependencies.Android.Compose.pager,
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

            commonDependecies.forEach { export(it) }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                commonDependecies.forEach { api(it) }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                androidDependencies.forEach { api(it) }
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
