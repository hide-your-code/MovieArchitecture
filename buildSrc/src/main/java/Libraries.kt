object Libraries {

    object Versions {
        const val appCompat = "1.2.0"
        const val annotations = "1.1.0"
        const val ktx = "1.3.2"
        const val gson = "2.8.6"
        const val fragment = "1.3.0-beta02"
        const val detekt = "1.15.0"

        //region Jetpack
        // Lifecycle
        const val lifecycle = "2.2.0"

        // Paging
        const val paging = "3.0.0-alpha12"

        // Room
        const val room = "2.3.0-alpha03"

        // Navigation
        const val navigation = "2.3.1"
        //endregion

        // Dependency Injection
        const val hilt = "2.29.1-alpha"
        const val hiltAndroidX = "1.0.0-alpha02"
        const val javaxAnnotations = "1.0"
        const val javaxInject = "1"

        // Server
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.9.0"

        // Logging
        const val timber = "4.7.1"

        // Image
        const val glide = "4.11.0"

        // UI
        const val constraintLayout = "2.0.4"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val material = "1.2.1"

        // Firebase
        const val firebaseBom = "26.2.0"

        // ViewPager 2
        const val viewPager2 = "1.0.0"

        // Indicator
        const val indicator = "4.1.2"

        // Check leak memory
        const val leakCanary = "2.6"
    }

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${KotlinVersions.standardLibrary}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinVersions.coroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${KotlinVersions.coroutines}"

    // App
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val androidAnnotations = "androidx.annotation:annotation:${Versions.annotations}"
    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.javaxAnnotations}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"

    // Server
    const val retrofit = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // UI
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // Image
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Json
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Dependency Injection
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidX}"
    const val hiltLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroidX}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"

    // Kotlin Lint
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAnalytic = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    //region Jetpack
    // Lifecycle
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"

    // Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

    // Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // ViewPager 2
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"

    // Ink page indicator
    const val indicator = "com.tbuonomo.andrui:viewpagerdotsindicator:${Versions.indicator}"

    // Leak Canary
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    //endregion
}
