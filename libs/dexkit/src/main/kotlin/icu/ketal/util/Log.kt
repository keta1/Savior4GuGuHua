package icu.ketal.util

import com.sun.jna.Callback
import com.sun.jna.Library

@Suppress("FunctionName", "ClassName")
interface Log : Library {
    fun interface log_callback_t : Callback {
        operator fun invoke(log: String)
    }

    fun setup_log_stream(verbose: Boolean, callback: log_callback_t)

    companion object {
        val instance: Log by nativeLib("dexkit")
    }
}