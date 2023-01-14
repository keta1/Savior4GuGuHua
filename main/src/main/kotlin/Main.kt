import io.luckypray.dexkit.DexKitBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Locale

val isWindows
    get() = System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")

fun loadLibrary(name: String) {
    System.loadLibrary(if (isWindows) "lib$name" else name)
}

suspend fun main() {
    loadLibrary("dexkit")
    withContext(Dispatchers.IO) {
        val file = File("dex/play/8.2.11_1380.apk")
        find(file.absolutePath)
    }
}

suspend fun find(path: String) {
    DexKitBridge.create(path)?.use { kit ->
        println(kit.getDexNum())
        val res = kit.findMethodUsingString("imei")
        res.forEach {
            println(it.descriptor)
        }
    }
}