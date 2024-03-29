plugins {
    id(BuildPlugins.androidApplicationPlugin)
    kotlin(BuildPlugins.kotlinAndroidPlugin)
    kotlin(BuildPlugins.kotlinKaptPlugin)
    id(BuildPlugins.hiltPlugin)
    id(BuildPlugins.navigationPlugin)
    id(BuildPlugins.googleServicePlugin)
    id(BuildPlugins.crashlyticsPlugin)
    id(BuildPlugins.detektGradlePlugin).version(Libraries.Versions.detekt)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        applicationId = AndroidClient.appId
        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName
        testInstrumentationRunner = AndroidClient.testRunner
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs +
                "-Xallow-result-return-type" +
                "-Xopt-in=kotlin.RequiresOptIn"
    }
}

detekt {
    autoCorrect = true
    // config rules file
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files(
        "${project.rootDir}/mobile/src/main/java",
        "${project.rootDir}/shared/src/main/java",
        "${project.rootDir}/model/src/main/java",
        "${project.rootDir}/buildSrc/src/main/java"
    )
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        this.jvmTarget = "11"
    }
}

dependencies {
    dependencyModule(Modules.shared)
    dependencyModule(Modules.model)

    debugImplementation(Libraries.leakCanary)

    testImplementation(Libraries.junit)
    testImplementation(Libraries.robolectric)
    testImplementation(Libraries.mockito)
    testImplementation(Libraries.mock_k)
    testImplementation(Libraries.mock_k_android)
    testImplementation(Libraries.power_mock_junit)
    testImplementation(Libraries.power_mock_mockito)

    kapt(Libraries.lifecycleCompiler)
    kapt(Libraries.hiltCompiler)
    kapt(Libraries.hiltAndroidXCompiler)
    kapt(Libraries.roomCompiler)
    kapt(Libraries.glideCompiler)
    compileOnly(Libraries.javaxAnnotation)
    compileOnly(Libraries.javaxInject)

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.viewModel)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.hilt)
    implementation(Libraries.hiltLifeCycle)
    implementation(Libraries.liveData)
    implementation(Libraries.lifecycleJava8)
    implementation(Libraries.fragment)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)
    implementation(Libraries.timber)
    implementation(Libraries.okHttpLoggingInterceptor)
    implementation(Libraries.retrofit)
    implementation(Libraries.room)
    implementation(Libraries.roomKtx)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationRuntime)
    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAnalytic)
    implementation(Libraries.firebaseCrashlytics)
    implementation(Libraries.viewPager2)
    implementation(Libraries.indicator)
    implementation(Libraries.glide)
    implementation(Libraries.paging)
    implementation(Libraries.shimmer)
}
