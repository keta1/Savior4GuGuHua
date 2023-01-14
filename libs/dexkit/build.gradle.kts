plugins {
    kotlin("jvm")
    `java-library`
    id("io.github.tomtzook.gradle-cmake") version "1.2.2"
}

dependencies {
}

sourceSets {
    val main by getting {
        java.setSrcDirs(listOf("DexKit/Android/dexkit/src/main/java"))
    }
}

cmake {
    targets {
        val cc by creating {
            cmakeLists.set(file("src/main/cpp/CMakeLists.txt"))
            targetMachines.add(machines.host)
        }
    }
}
