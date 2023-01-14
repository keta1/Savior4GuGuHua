plugins {
    kotlin("jvm")
    `java-library`
    id("io.github.tomtzook.gradle-cmake") version "1.2.2"
}

dependencies {
}

cmake {
    targets {
        val cc by creating {
            cmakeLists.set(file("src/main/cpp/CMakeLists.txt"))
            targetMachines.add(machines.host)
        }
    }
}
