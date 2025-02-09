plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:datetime"))
                implementation(project(":core:displaymodels"))
                implementation(project(":core:feature"))
                implementation(project(":core:models"))
                implementation(project(":data:event"))
                implementation(project(":data:game"))
                implementation(project(":data:local-sqldelight"))
                implementation(project(":data:match"))
                implementation(project(":data:octanegg"))
                implementation(project(":data:remote"))
                implementation(project(":data:team"))
                implementation(project(":feature:event-detail"))
                implementation(project(":feature:feed"))
                implementation(libs.bundles.ktor.client)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.square.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(project(":core:displaymodels-test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
        val androidTest by getting
        maybeCreate("iosX64Main")
        maybeCreate("iosArm64Main")
        maybeCreate("iosSimulatorArm64Main")
        maybeCreate("iosMain").apply {
            dependsOn(commonMain)
            getAt("iosX64Main").dependsOn(this)
            getAt("iosArm64Main").dependsOn(this)
            getAt("iosSimulatorArm64Main").dependsOn(this)

            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
        maybeCreate("iosX64Test")
        maybeCreate("iosArm64Test")
        maybeCreate("iosSimulatorArm64Test")
        maybeCreate("iosTest").apply {
            dependsOn(commonTest)
            getAt("iosX64Test").dependsOn(this)
            getAt("iosArm64Test").dependsOn(this)
            getAt("iosSimulatorArm64Test").dependsOn(this)
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
//        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.adammcneilly.pocketleague.shared"
}

project.extensions.findByType(org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension::class.java)?.apply {
    if (project.findProperty("ios") == "true") {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = project.name
            }
        }
    }
    if (project.findProperty("js") == "true") {
        js(IR) {
            browser()
        }
    }
}
