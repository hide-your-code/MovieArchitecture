buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.kotlinGradle)
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.hiltGradle)
        classpath(BuildPlugins.navigationGradle)
        classpath(BuildPlugins.googleServiceGradle)
        classpath(BuildPlugins.crashlyticsGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
