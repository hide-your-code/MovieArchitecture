plugins {
    id(BuildPlugins.javaPlugin)
    kotlin(BuildPlugins.kotlinPlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Libraries.ktxCore)
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.gson)
}
