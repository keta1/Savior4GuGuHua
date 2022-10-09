@file:JvmName("HostInfo")
package io.github.qauxv.util

fun requireMinQQVersion(versionCode: Long): Boolean {
    return requireMinVersion(versionCode, HostSpecies.QQ)
}

fun requireMinVersion(versionCode: Long, hostSpecies: HostSpecies): Boolean {
    return true // TODO hostInfo.hostSpecies == hostSpecies && hostInfo.versionCode >= versionCode
}

enum class HostSpecies {
    QQ,
    TIM,
    QQ_Play,
    QQ_Lite,
    QQ_International,
    QQ_HD,
    QAuxiliary,
    Unknown
}

