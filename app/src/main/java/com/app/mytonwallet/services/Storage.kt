package com.app.mytonwallet.services

import android.content.Context
import android.content.SharedPreferences
import com.app.mytonwallet.Constants.SHARED_PREFS_STORAGE_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NativeStorage(context: Context) : Storage {

    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences(SHARED_PREFS_STORAGE_NAME, Context.MODE_PRIVATE)

    override suspend fun getItem(name: String): Any? {
        return withContext(Dispatchers.IO) {
            sharedPreferences.all[name]
        }
    }

//    @JavascriptInterface
    override suspend fun getItem(name: StorageKey, force: Boolean): Any? {
        return getItem(name)
    }

    override suspend fun setItem(name: String, value: Any) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                extracted(name, value)
                apply()
            }
        }
    }

//    @JavascriptInterface
    override suspend fun setItem(name: StorageKey, value: Any) {
        setItem(name.key, value)
    }

//    @JavascriptInterface
    override suspend fun removeItem(name: StorageKey) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().remove(name.key).apply()
        }
    }

//    @JavascriptInterface
    override suspend fun clear() {
//        withContext(Dispatchers.IO) {
//            sharedPreferences.edit().clear().apply()
//        }
    }

//    @JavascriptInterface
    override suspend fun getAll(): Map<String, Any>? {
        return withContext(Dispatchers.IO) {
            sharedPreferences.all.mapValues { it.value ?: "" } // handle null values
        }
    }

//    @JavascriptInterface
    override suspend fun setMany(items: Map<String, Any>) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                for ((key, value) in items) {
                    extracted(key, value)
                }
                apply()
            }
        }
    }

//    @JavascriptInterface
    override suspend fun getMany(keys: List<String>): Map<String, Any>? {
        return withContext(Dispatchers.IO) {
            keys.associateWith { sharedPreferences.all[it] ?: "" }
        }
    }

    private fun SharedPreferences.Editor.extracted(
        key: String,
        value: Any
    ) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            else -> throw IllegalArgumentException("Unsupported value type")
        }
    }

}
