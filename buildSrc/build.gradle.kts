object Dependencies {
    object Version {
        const val detekt = "1.15.0-RC1"
        const val androidBuildTool = "7.0.1"
    }

    const val AndroidBuildTools = "com.android.tools.build:gradle:${Version.androidBuildTool}"
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
}
