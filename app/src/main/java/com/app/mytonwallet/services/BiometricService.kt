package com.app.mytonwallet.services

import android.content.Context
import com.app.mytonwallet.Constants.BIOMETRIC_AUTH_KEY
import com.app.mytonwallet.Constants.SHARED_PREFS_BIOMETRIC_NAME

class BiometricService(val context: Context) {

    private val sharedPref = context.getSharedPreferences(SHARED_PREFS_BIOMETRIC_NAME, Context.MODE_PRIVATE)

    fun saveAuthenticationStatus(isAuthenticated: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(BIOMETRIC_AUTH_KEY, isAuthenticated)
            apply()
        }
    }

    fun getAuthenticationStatus(): Boolean {
        return sharedPref.getBoolean(BIOMETRIC_AUTH_KEY, false)
    }
}