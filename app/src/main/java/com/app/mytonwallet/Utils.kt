package com.app.mytonwallet

import io.ktor.util.hex
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.crypto.Ed25519
import org.ton.lite.client.LiteClient
import org.ton.mnemonic.Mnemonic

object Utils {

    private val DEFAULT_WALLET_VERSION = "W4R2"

    fun toKeyPair(mnemonic: List<String>, password: String?): Pair<String, String> {
        val secretKey: ByteArray = Mnemonic.toSeed(mnemonic, password.orEmpty())
        return Pair(hex(Ed25519.publicKey(secretKey)), hex(secretKey))
    }

    fun buildTransactionUrl(txHex: String): String {
        return "https://testnet.tonviewer.com/transaction/${txHex}"
    }

    fun privateKeyToAddress(pk: String, liteClient: LiteClient) {
        val ed25519 = PrivateKeyEd25519.of(pk.toByteArray())
        WalletV4R2Contract.address(ed25519).toString(
            userFriendly = true, urlSafe = true, testOnly = true, bounceable = true
        )
    }

}