import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    java
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
    application
}

group = "icu.ketal"
version = "1.0-SNAPSHOT"

val library: String = buildDir.resolve("library").absolutePath
val args = arrayOf(
    "-Djava.library.path=$library"
)

application {
    applicationDefaultJvmArgs += args
}

dependencies {
    implementation(projects.libs.stub)
    implementation(projects.libs.dexkit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.livefront.sealed-enum:runtime:0.6.0")
    ksp("com.github.livefront.sealed-enum:ksp:0.6.0")
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

fun afterEval() {
    val cmakeBuild by tasks.registering {
        group = "build"
        val build = project(":libs:dexkit").tasks.getByName("cmakeBuild")
        dependsOn(build)
        doLast {
            val outDir = project(":libs:dexkit").buildDir.resolve("cmake/cc").listFiles()!!.first()
            val libs = outDir.listFiles()!!.filter { it.name.startsWith("lib") }
            libs.forEach {
                it.copyTo(file("$library/${it.name}"), true)
            }
        }
    }
    tasks.jar.get().dependsOn(cmakeBuild)
}

afterEvaluate {
    afterEval()
}

evaluationDependsOn(":libs:dexkit")
