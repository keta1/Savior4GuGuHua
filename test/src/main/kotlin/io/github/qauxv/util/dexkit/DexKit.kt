package io.github.qauxv.util.dexkit

import io.github.qauxv.util.Log
import io.luckypray.dexkit.DexKitBridgeProvider

object DexKit {
    private const val NO_SUCH_CLASS = "Lio/github/qauxv/util/DexKit\$NoSuchClass;"

    @JvmField
    val NO_SUCH_METHOD = DexMethodDescriptor(NO_SUCH_CLASS, "a", "()V")

    context(DexKitBridgeProvider)
    fun doBatchFindMethodImpl(targetArray: Array<DexKitTarget>) {

    }

    context(DexKitBridgeProvider)
    fun doFindMethodImpl(target: DexKitTarget): DexMethodDescriptor? {
        if (target !is DexKitTarget.UsingStr) return null
        val keys = target.traitString
        val methods = keys.map { key ->
            helper.findMethodUsingString(key, true)
        }.flatMap { desc ->
            desc.map { DexMethodDescriptor(it.descriptor) }
        }
        var ret: DexMethodDescriptor? = null
        if (methods.isNotEmpty()) {
            ret = target.verifyTargetMethod(methods)
            if (ret == null) {
                methods.map { it.toString() }.forEach(Log::i)
                Log.e("${methods.size} methods found for ${target.name}, none satisfactory, save null.")
                ret = NO_SUCH_METHOD
            }
            Log.d("save id: ${target.name},method: $ret")
        }
        return ret
    }
}
