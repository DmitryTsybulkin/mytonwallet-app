package com.app.mytonwallet.web

import android.webkit.JavascriptInterface

class WebAppInterface(val listener: (Message) -> Unit) {
    @JavascriptInterface
    fun generateMnemonic(mnemonic: Array<String>) {
        listener(GenerateMnemonicMsg(mnemonic))
    }

    @JavascriptInterface
    fun getMnemonicWordList(words: Array<String>) {
        listener(GetMnemonicWordListMsg(words))
    }

    @JavascriptInterface
    fun validateMnemonic(valid: Boolean) {
        listener(ValidateMnemonicMsg(valid))
    }

    /**
     * walletFromMnemonic
     * getBalance
     * getTokenBalance
     * getJettonBalance?
     *
     * getAccountTransactionSlice,
     * getTokenTransactionSlice,
     * decrypt
     *
     * Bonus:
     * encrypt
     * sendTx, prepareTx,
     * calculateFee
     */
}