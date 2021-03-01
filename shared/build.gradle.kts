import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.androidLibraryPlugin)
    kotlin(BuildPlugins.kotlinAndroidPlugin)
    kotlin(BuildPlugins.kotlinKaptPlugin)
    id(BuildPlugins.hiltPlugin)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName
        testInstrumentationRunner = AndroidClient.testRunner

        buildConfigField("String", "API_KEY", getSecrets()["API_KEY"] as String)
        buildConfigField("String", "BASE_URL", getSecrets()["BASE_URL"] as String)
        buildConfigField("String", "DATABASE_NAME", getSecrets()["DATABASE_NAME"] as String)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs +
                "-Xallow-result-return-type" +
                "-Xopt-in=kotlin.RequiresOptIn"
    }
}

fun getSecrets(): Properties = Properties().apply {
    load(FileInputStream(File("secrets.properties")))
}

dependencies {
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
    implementation(Libraries.paging)
}
