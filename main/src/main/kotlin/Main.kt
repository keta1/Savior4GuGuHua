import io.luckypray.dexkit.DexKitBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Locale

val isWindows
    get() = System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")

fun loadDexKit() {
    if (isWindows) {
        System.loadLibrary("libdexkit")
    } else {
        System.loadLibrary("dexkit")
    }
}

suspend fun main() {
    loadDexKit()
    withContext(Dispatchers.IO) {
        val file = File("dex/play/8.2.11_1380.apk")
        println(file.exists())
        find(file.absolutePath)
    }
}

suspend fun find(path: String) {
    DexKitBridge.create(path).use {
        with(it) {
            println(helper.getDexNum())
            val res = helper.findMethodUsingString("imei")
            res.forEach {
                println(it.descriptor)
            }
        }
    }
}