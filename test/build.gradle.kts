import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    java
    id("com.google.devtools.ksp") version "1.7.20-1.0.6"
    application
}

group = "icu.ketal"
version = "1.0-SNAPSHOT"

val library: String = file("${project(":libs:dexkit").buildDir}/cmake/cc/linux-amd64").absolutePath
println(library)
val args = arrayOf(
    "-Djava.library.path=$library",
    "-Djna.library.path=$library"
)

application {
    applicationDefaultJvmArgs += args
}

dependencies {
    implementation(projects.libs.stub)
    implementation(projects.libs.dexkit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.livefront.sealed-enum:runtime:0.5.0")
    ksp("com.github.livefront.sealed-enum:ksp:0.5.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions{
        jvmTarget = "11"
        freeCompilerArgs += "-Xcontext-receivers"
    }
    sourceSets.configureEach {
        kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
    }
}

application {
    mainClass.set("MainKt")
}
