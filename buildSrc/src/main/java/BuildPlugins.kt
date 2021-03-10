object BuildPlugins {
    object Versions {
        const val buildTools = "4.1.2"
        const val hilt = "2.33-beta"
        const val navigation = "2.3.4"
        const val googlePlayService = "4.3.5"
        const val firebaseAnalytics = "2.5.2"
    }

    // Java
    const val javaPlugin = "java-library"

    // Android
    const val androidGradle = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val androidApplicationPlugin = "com.android.application"
    const val androidLibraryPlugin = "com.android.library"

    // Kotlin
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinVersions.standardLibrary}"
    const val kotlinAndroidPlugin = "android"
    const val kotlinKaptPlugin = "kapt"
    const val kotlinPlugin = "jvm"

    // Dependency Injection
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hiltPlugin = "dagger.hilt.android.plugin"

    // Navigation
    const val navigationGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val navigationPlugin = "androidx.navigation.safeargs.kotlin"

    // Google service
    const val googleServiceGradle = "com.google.gms:google-services:${Versions.googlePlayService}"
    const val googleServicePlugin = "com.google.gms.google-services"

    // Firebase
    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseAnalytics}"
    const val crashlyticsPlugin = "com.google.firebase.crashlytics"

    // Kotlin Lint
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt"
}
