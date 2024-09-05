package com.app.mytonwallet.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.mytonwallet.Constants
import com.app.mytonwallet.Constants.NETWORK

class EncryptedFieldVM (private val sharedPreferences: SharedPreferences) : ViewModel() {
    val decryptedText = mutableStateOf("")

    fun decrypt(txComment: String, fromAddress: String) {
//        val txCommentData = Utils.base64ToUnsignedBytes(txComment)
//        val publicKey = sharedPreferences.getString(Constants.PRIVATE_KEY_KEY, "")
//        val privateKey = sharedPreferences.getString(Constants.PUBLIC_KEY_KEY, "")

//        if (privateKey != null && publicKey != null) {
//            var publicKeyArr = Utils.signedBytesToUnsigned(publicKey.toByteArray())
//
//            var privateKeyArr = Utils.signedBytesToUnsigned(privateKey.toByteArray())
//            if (privateKeyArr.size == 64) {
//                privateKeyArr = privateKeyArr.take(32).toIntArray()
//            }
//
//            val address = Address.of(fromAddress)
//            val base64Address = address.toString(false, true, true, NETWORK == "testnet")
//            val salt = Utils.signedBytesToUnsigned(base64Address.toByteArray())

//            val theirPublicKey = IntArray(publicKeyArr.size)
//
//            for (i in publicKeyArr.indices) {
//                theirPublicKey[i] = txCommentData[i] xor publicKeyArr[i]
//            }
//
//
//            getSharedSecrets(intsToBytes(theirPublicKey), intsToBytes(privateKeyArr))
//        } else {
//            throw IllegalStateException("You are not authorised in the Wallet")
//        }
    }

    private fun getSharedSecrets(theirPublicKeyBytes: ByteArray, privateKey: ByteArray): ByteArray? {
//        val keyFactory = KeyFactory.getInstance("X25519", "BC")
//
//        val theirPublicKey = keyFactory.generatePublic(X509EncodedKeySpec(theirPublicKeyBytes))
//        val keySpec = PKCS8EncodedKeySpec(privateKey)
//        // Perform key agreement (shared secret)
//        val pk = keyFactory.generatePrivate(keySpec)
//
//        val keyAgreement = KeyAgreement.getInstance("X25519", "BC")
//
//        keyAgreement.init(pk)
//        keyAgreement.doPhase(theirPublicKey, true)
//        val sharedSecret = keyAgreement.generateSecret()
//
//        return sharedSecret
        return TODO("Provide the return value")
    }

    private fun intsToBytes(arr: IntArray): ByteArray {
        return arr.map { it.toByte() }.toByteArray()
    }
}