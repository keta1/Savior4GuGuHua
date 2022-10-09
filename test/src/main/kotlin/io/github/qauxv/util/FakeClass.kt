package io.github.qauxv.util

import io.luckypray.dexkit.DexKitBridgeProvider

data class FakeClass(
    val typeSig: String
) {
    val desc = typeSig
    companion object {

    }
}

context(DexKitBridgeProvider)
fun load(className: String): FakeClass? {
    return null
}