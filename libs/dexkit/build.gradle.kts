plugins {
    kotlin("jvm")
    `java-library`
    id("io.github.tomtzook.gradle-cmake") version "1.2.2"
}

dependencies {
    api("net.java.dev.jna:jna:5.12.1")
}

cmake {
    targets {
        val cc by creating {
            cmakeLists.set(file("src/main/cpp/CMakeLists.txt"))
            targetMachines.add(machines.host)
        }
    }
}
