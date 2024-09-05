package com.app.mytonwallet.services

sealed class StorageKey(val key: String) {
    data object MnemonicsEncrypted : StorageKey("mnemonicsEncrypted")
    data object Accounts : StorageKey("accounts")
    data object StateVersion : StorageKey("stateVersion")
    data object CurrentAccountId : StorageKey("currentAccountId")
    data object ClientId : StorageKey("clientId")
    data object BaseCurrency : StorageKey("baseCurrency")

    // For extension
    data object Dapps : StorageKey("dapps")
    data object DappMethodsLastAccountId : StorageKey("dappMethods:lastAccountId")
    data object WindowId : StorageKey("windowId")
    data object WindowState : StorageKey("windowState")
    data object IsTonMagicEnabled : StorageKey("isTonMagicEnabled")
    data object IsTonProxyEnabled : StorageKey("isTonProxyEnabled")
    data object IsDeeplinkHookEnabled : StorageKey("isDeeplinkHookEnabled")

    // For TonConnect SSE
    data object SseLastEventId : StorageKey("sseLastEventId")
}

/**
 * export interface Storage {
 * getItem(name: StorageKey, force?: boolean): Promise<any>;
 *
 * setItem(name: StorageKey, value: any): Promise<void>;
 *
 * removeItem(name: StorageKey): Promise<void>;
 *
 * clear(): Promise<void>;
 *
 * getAll?(): Promise<AnyLiteral>;
 *
 * setMany?(items: AnyLiteral): Promise<void>;
 *
 * getMany?(keys: string[]): Promise<AnyLiteral>;
 * }
 */
interface Storage {
    suspend fun getItem(name: StorageKey, force: Boolean = false): Any?

    suspend fun setItem(name: StorageKey, value: Any): Unit

    suspend fun removeItem(name: StorageKey): Unit

    suspend fun clear(): Unit

    suspend fun getAll(): Map<String, Any>?

    suspend fun setMany(items: Map<String, Any>): Unit // Assuming AnyLiteral is Map<String, Any>

    suspend fun getMany(keys: List<String>): Map<String, Any>?
    suspend fun getItem(name: String): Any?
    suspend fun setItem(name: String, value: Any)
}
