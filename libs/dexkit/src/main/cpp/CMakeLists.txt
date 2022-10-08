cmake_minimum_required(VERSION 3.10)
project(dexkit)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(../../../DexKit/Core Core)

find_package(JNI REQUIRED)
include_directories(${JAVA_INCLUDE_PATH})
include_directories(${JAVA_INCLUDE_PATH2})

include_directories(include)

add_library(log SHARED
        log/nativestreambuf.h
        log/log.cc)

add_library(${PROJECT_NAME} SHARED
        core/native-bridge.cpp)

target_link_libraries(${PROJECT_NAME}
        dex_kit_static)
