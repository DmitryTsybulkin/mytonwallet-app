package com.app.mytonwallet.web

import java.util.EventListener

@FunctionalInterface
interface MessageListener : EventListener {
    fun onGenerateMnemonicMsg(message: GenerateMnemonicMsg)
}
