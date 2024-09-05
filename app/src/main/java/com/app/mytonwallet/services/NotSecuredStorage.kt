package com.app.mytonwallet.services

import android.content.Context
import com.app.mytonwallet.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotSecuredStorage(val context: Context) {

    private val sharedPref = context.getSharedPreferences(Constants.SHARED_PREFS_BIOMETRIC_NAME, Context.MODE_PRIVATE)

    fun save(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun save(key: String, value: List<String>) {
        with(sharedPref.edit()) {
            putStringSet(key, HashSet(value))
            apply()
        }
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, "")
    }

    fun exists(key: String): Boolean {
        return sharedPref.contains(key)
    }

    fun getStringSet(key: String): MutableSet<String>? {
        return sharedPref.getStringSet(key, emptySet())
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            sharedPref.edit().clear().apply()
        }
    }

}