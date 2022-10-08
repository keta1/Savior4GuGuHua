import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
}

group = "icu.ketal"
version = "1.0-SNAPSHOT"

application {
    applicationDefaultJvmArgs += "-Djava.library.path=" +
            file("${project(":libs:dexkit").buildDir}/lib/main/debug").absolutePath
}

dependencies {
    implementation(projects.libs.dexkit)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}
