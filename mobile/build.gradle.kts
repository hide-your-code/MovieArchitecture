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
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xallow-result-return-type"
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
        this.jvmTarget = "1.8"
    }
}

dependencies {
    dependencyModule(Modules.shared)
    dependencyModule(Modules.model)

    debugImplementation(Libraries.leakCanary)

    kapt(Libraries.lifecycleCompiler)
    kapt(Libraries.hiltCompiler)
    kapt(Libraries.hiltAndroidXCompiler)
    kapt(Libraries.roomCompiler)
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
}