// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

//buildscript {
//    val kotlinVersion by extra("1.9.0") // Define a versão do Kotlin
//
//    repositories {
//        google()
//        jcenter()
//        mavenCentral()
//    }
//    dependencies {
//        classpath("com.android.tools.build:gradle:8.6.0")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle.kts files
//    }
//}
//
//tasks.register<Delete>("clean") {
//    delete(rootProject.buildDir)
//}
