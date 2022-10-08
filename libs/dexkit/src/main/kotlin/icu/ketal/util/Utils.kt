package icu.ketal.util

import com.sun.jna.Library
import com.sun.jna.Native

inline fun <reified T : Library> nativeLib(name: String): Lazy<T> = lazy {
    Native.load(name, T::class.java)
}