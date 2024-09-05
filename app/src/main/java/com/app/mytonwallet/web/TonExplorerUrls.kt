package com.app.mytonwallet.web

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object TonExplorerUrls {
    private const val TON_EXPLORER_BASE_MAINNET_URL = "https://tonviewer.com/"
    private const val TON_EXPLORER_BASE_TESTNET_URL = "https://testnet.tonviewer.com/"

    private fun getTonExplorerBaseUrl(isTestnet: Boolean): String {
        return if (isTestnet) TON_EXPLORER_BASE_TESTNET_URL else TON_EXPLORER_BASE_MAINNET_URL
    }

    fun getTonExplorerTransactionUrl(transactionHash: String?, isTestnet: Boolean): String? {
        if (transactionHash == null || transactionHash == "NOHASH") {
            return null
        }
        return "${getTonExplorerBaseUrl(isTestnet)}transaction/${base64ToHex(transactionHash)}"
    }

    @OptIn(ExperimentalEncodingApi::class, ExperimentalStdlibApi::class)
    private fun base64ToHex(transactionHash: String): String {
        val decoded = Base64.decode(transactionHash, 0, transactionHash.length - 1)
        return decoded.toHexString()
    }
}
