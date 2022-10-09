import io.github.qauxv.util.Log
import io.github.qauxv.util.dexkit.CDialogUtil
import io.github.qauxv.util.dexkit.DexKit
import io.luckypray.dexkit.DexKitBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun main() {
    icu.ketal.util.Log.instance.setup_log_stream(true) {
        if (it.isEmpty()) return@setup_log_stream
        when (it.first()) {
            'F' -> println("Fatal: " + it.drop(1))
            'D' -> println("Debug: " + it.drop(1))
            'I' -> println("Info: " + it.drop(1))
            'W' -> println("Warn: " + it.drop(1))
            'E' -> println("Error: " + it.drop(1))
            else -> println("Debug: $it")
        }
    }

    withContext(Dispatchers.IO) {
        find("dex/play/8.2.11_1380.apk")
    }
}

suspend fun find(path: String) {
    DexKitBridge.create(path).use {
        with(it) {
            val find = DexKit.doFindMethodImpl(CDialogUtil)
            Log.e(find.toString())
        }
    }
}