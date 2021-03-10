plugins {
    id(BuildPlugins.javaPlugin)
    kotlin(BuildPlugins.kotlinPlugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Libraries.ktxCore)
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.gson)
}
