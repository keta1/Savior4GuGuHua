import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `java-library`
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
