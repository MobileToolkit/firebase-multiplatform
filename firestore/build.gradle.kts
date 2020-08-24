import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
//    id("maven-publish")
}

android {
    multiplatformLibrary()
}

dependencies {}

kotlin {
    android {
        publishAllLibraryVariants()
    }

    val iosTargetConfig: KotlinNativeTarget.() -> Unit = {
        val firestoreBinariesPath = "${rootDir}/Carthage/Build/iOS"

        binaries {
            framework {
                baseName = "MT_Firestore"
                freeCompilerArgs = listOf("-Xobjc-generics")
                isStatic = true
                linkerOpts(
                    "-F$firestoreBinariesPath",
                    "-framework",
                    "SystemConfiguration",
                    "-lz"
                )
                transitiveExport = true
            }
        }

        compilations.getByName("main") {
            cinterops.create("Firestore") {
                includeDirs(
                    "$firestoreBinariesPath/FirebaseCore.framework/Headers",
                    "$firestoreBinariesPath/FirebaseFirestore.framework/Headers",
                    "$firestoreBinariesPath/GoogleUtilities.framework/Headers"
                )
                extraOpts("-verbose")
            }
        }
    }

    if (project.extra["ideActive"] as Boolean)
        iosX64("ios", iosTargetConfig)
    else
        ios(configure = iosTargetConfig)

    sourceSets {
        all {
            languageSettings.apply {
//                apiVersion = "1.4"
//                enableLanguageFeature("InlineClasses")
//                languageVersion = "1.4"
                progressiveMode = true
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }

        commonMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Kotlinx.SERIALIZATION}")
                api("org.jetbrains.kotlinx:kotlinx-serialization-properties:${Versions.Kotlinx.SERIALIZATION}")
            }
        }

        getByName("androidMain") {
            dependencies {
                api("com.google.firebase:firebase-firestore:21.5.0")
            }
        }
    }
}

//publishing {
//    repositories {
//        githubPackages()
//    }
//}

val buildFatFramework by tasks.creating(FatFrameworkTask::class) {
    group = "build"

    val frameworks = kotlin.targets.filterIsInstance<KotlinNativeTarget>().map { it.binaries.getFramework("RELEASE") }

    baseName = frameworks.first().baseName

    from(frameworks)
}

//group 'org.mobiletoolkit'
//version '1.0.0'
//
//apply plugin: 'maven-publish'
//
//dependencies {
//    implementation 'androidx.appcompat:appcompat:1.0.2'
//    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
//    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7'
//
//    testImplementation "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
//}
//
//ext {
//    iosFrameworkName = 'MobileToolkitFirestore'
//}
//
//kotlin {
//    android('android')
//
//    targets {
//        final Boolean isDeviceBuild = System.getenv('SDK_NAME')?.startsWith('iphoneos')
//        final def iosTarget = isDeviceBuild ? presets.iosArm64 : presets.iosX64
//        final String buildType = project.findProperty('XCODE_CONFIGURATION')?.toUpperCase() ?: 'DEBUG'
//        final String bitcodeFlag = isDeviceBuild ? ( buildType == 'DEBUG' ? 'marker' : 'bitcode' ) : 'disable'
//
//        fromPreset(iosTarget, 'ios') {
//            binaries {
//                framework("${iosFrameworkName}") {
//                    embedBitcode = bitcodeFlag
////                    embedBitcode 'disable'
////                    embedBitcode 'marker' // for debug builds
////                    embedBitcode 'bitcode' // for release builds
//                    isStatic = true
//                    freeCompilerArgs += '-Xobjc-generics'
//                    freeCompilerArgs += '-Xuse-experimental=kotlin.Experimental'
//                }
//            }
//            compilations.main {
//                cinterops {
//                    firestore {
//                        packageName 'com.google.firebase.firestore'
//                        includeDirs "${rootProject.iosCinteropsPath}/FirebaseFirestore.framework/Headers"
//                    }
//                }
//            }
//        }
//    }
//    sourceSets {
//        commonMain {
//            dependencies {
//                implementation kotlin('stdlib-common')
////                api "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinCoroutinesVersion"
//                api "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinSerializationVersion"
//            }
//        }
//        commonTest {
//            dependencies {
//                implementation kotlin('test-common')
//                implementation kotlin('test-annotations-common')
//            }
//        }
//        androidMain {
//            dependencies {
//                implementation kotlin('stdlib')
//                api 'com.google.firebase:firebase-firestore:20.2.0'
////                api "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion"
////                api "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion"
//                api "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinSerializationVersion"
//            }
//        }
//        androidTest {
//            dependencies {
//                implementation kotlin('test')
//                implementation kotlin('test-junit')
//            }
//        }
//        iosMain {
//            dependencies {
////                api "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinCoroutinesVersion"
//                api "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$kotlinSerializationVersion"
//            }
//        }
//        iosTest {
//        }
//    }
//}
//
//task packForXcode {
//    final File frameworkDir = new File(rootProject.buildDir, 'xcode-frameworks')
//    final String buildType = project.findProperty('XCODE_CONFIGURATION')?.toUpperCase() ?: 'DEBUG'
//
//    def keyFrameworkPrefix = "${iosFrameworkName}${buildType.toLowerCase().capitalize()}"
//    dependsOn "link${keyFrameworkPrefix}FrameworkIos"
//
//    doLast {
//        def srcFile = kotlin.targets.ios.binaries.getFramework("${iosFrameworkName}", buildType).outputFile
//
//        copy {
//            from srcFile.parent
//            into frameworkDir
//        }
//    }
//}
//
//tasks.build.dependsOn packForXcode
//
//cinteropFirestoreIos.dependsOn rootProject.tasks.carthageBuild