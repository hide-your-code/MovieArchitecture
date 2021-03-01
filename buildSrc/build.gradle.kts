object Dependencies {
    object Version {
        const val detekt = "1.15.0-RC1"
        const val androidBuildTool = "4.1.2"
    }

    const val AndroidBuildTools = "com.android.tools.build:gradle:${Version.androidBuildTool}"
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
}
