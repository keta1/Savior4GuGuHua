package io.github.qauxv.util

object Log {

    @JvmStatic
    fun d(msg: String) = println("Debug: $msg")

    @JvmStatic
    fun i(msg: String) = println("Info: $msg")
    @JvmStatic
    fun w(msg: String) = println("Warn: $msg")

    @JvmStatic
    fun e(msg: String) = println("Error: $msg")

    @JvmStatic
    fun e(msg: Throwable) = println("Error: $msg")
}
