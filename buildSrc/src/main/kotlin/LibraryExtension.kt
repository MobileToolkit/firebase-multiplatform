import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion

/**
 * Created by Sebastian Owodzin on 23/06/2020
 */
fun LibraryExtension.androidLibrary() {
    compileSdkVersion(Versions.TARGET_ANDROID_SDK)

    defaultConfig {
        minSdkVersion(Versions.MIN_ANDROID_SDK)
        targetSdkVersion(Versions.TARGET_ANDROID_SDK)
        versionCode = Git.gitVersionCode()
        versionName = Git.gitVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments(mapOf("clearPackageData" to "true"))

        consumerProguardFiles("proguard-rules.pro")
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

fun LibraryExtension.multiplatformLibrary() {
    androidLibrary()

//    sourceSets {
//        getByName("main") {
//            manifest.srcFile("src/androidMain/AndroidManifest.xml")
//            java.srcDirs("src/androidMain/kotlin")
//            res.srcDirs("src/androidMain/res")
//        }
//    }
}
