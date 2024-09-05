package com.app.mytonwallet.web

interface Message

class GenerateMnemonicMsg(val mnemonic: Array<String>) : Message
class GetMnemonicWordListMsg(val words: Array<String>) : Message
class ValidateMnemonicMsg(val valid: Boolean) : Message