import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
        classpath("com.google.gms:google-services:4.3.3")
    }
}

allprojects {
    val ideActive by extra { gradleLocalProperties(rootDir)["idea.active"] == "true" }
    val groupId by extra { "org.mobiletoolkit.android.firebase" }
    val vcsUrl by extra { "https://github.com/MobileToolkit/firebase-android.git" }
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("carthageBuild", Exec::class) {
    executable = "sh"
    args("-c", "/usr/local/bin/carthage build --cache-builds --project-directory $rootDir")
}

tasks.register("carthageUpdate", Exec::class) {
    executable = "sh"
    args("-c", "/usr/local/bin/carthage update --cache-builds --project-directory $rootDir")
}
