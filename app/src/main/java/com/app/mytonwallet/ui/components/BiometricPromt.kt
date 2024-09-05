package com.app.mytonwallet.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun ShowBiometricPrompt(onAuthSuccess: () -> Unit,
                        onAuthError: (String) -> Unit,
                        onBiometricCall: (BiometricPrompt, BiometricPrompt.PromptInfo) -> Unit) {
    val context = LocalContext.current
    val executor = remember { ContextCompat.getMainExecutor(context) }

    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onAuthError(errString.toString())
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onAuthSuccess()
            }

        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Use account password")
        .build()

    onBiometricCall(biometricPrompt, promptInfo)
}