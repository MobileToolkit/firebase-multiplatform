plugins {
    id("com.android.application")
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    kotlin("android.extensions") version "1.4.0"
}

android {
    compileSdkVersion(Versions.TARGET_ANDROID_SDK)

    defaultConfig {
        minSdkVersion(Versions.MIN_ANDROID_SDK)
        targetSdkVersion(Versions.TARGET_ANDROID_SDK)
        versionCode = 1
        versionName = "1.0"
        applicationId = "org.mobiletoolkit.firebase.exampleapp"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments(mapOf("clearPackageData" to "true"))
    }

    buildTypes {
        val release by getting {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        pickFirst("META-INF/*.kotlin_module")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:${Versions.Androidx.APP_COMPAT}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.Androidx.CONSTRAINT_LAYOUT}")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
}

kotlin {
    android()

//    ios {
//
//    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":firestore"))
            }
        }
    }
}

apply(plugin = "com.google.gms.google-services")

//plugins {
//    id 'com.android.application'
//
//    id 'kotlin-multiplatform'
//    id 'kotlinx-serialization'
//    id 'org.jetbrains.kotlin.android.extensions'
//}
//
//android {
//    compileSdkVersion 28
//    defaultConfig {
//        applicationId 'org.mobiletoolkit.firebase.exampleapp'
//        minSdkVersion 16
//        targetSdkVersion 28
//        versionCode 1
//        versionName '1.0'
//        testInstrumentationRunner 'androidx.test.runner.AndroidJUnitRunner'
//        multiDexEnabled true
//    }
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    packagingOptions {
//        pickFirst 'META-INF/*.kotlin_module'
//    }
//}
//
//androidExtensions {
//    experimental = true
//}
//
//dependencies {
//    implementation 'androidx.appcompat:appcompat:1.0.2'
//    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
//    implementation 'androidx.multidex:multidex:2.0.1'
//    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
//    implementation 'com.google.android.material:material:1.0.0'
//
//    androidTestImplementation 'androidx.test:runner:1.2.0'
//}
//
//ext {
//    iosFrameworkName = 'ExampleAppShared'
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
//        }
//    }
//    sourceSets {
//        commonMain {
//            dependencies {
//                implementation kotlin('stdlib-common')
//                implementation project(':firestore')
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
//                implementation project(':firestore')
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
//                implementation project(':firestore')
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
//apply plugin: 'com.google.gms.google-services'