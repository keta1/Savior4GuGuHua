import io.github.qauxv.util.Log
import io.github.qauxv.util.dexkit.CDialogUtil
import io.github.qauxv.util.dexkit.DexKit
import io.luckypray.dexkit.DexKitBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun main() {
    withContext(Dispatchers.IO) {
        find("dex/play/8.2.11_1380.apk")
    }
}

suspend fun find(path: String) {
    DexKitBridge.create(path).use {
        with(it) {
            val find = DexKit.doFindMethodImpl(CDialogUtil)
            Log.i("find result: $find")
        }
    }
}