package com.app.mytonwallet.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.services.BiometricService
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.SecondaryButton
import com.app.mytonwallet.ui.components.ShowBiometricPrompt
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.MyTonWalletTheme

@Composable
fun Biometric(
    navController: NavHostController,
    biometricService: BiometricService
) {
    var showBiometricView by remember {
        mutableStateOf(false)
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_fingerprint),
            contentDescription = "Fingerprint",
            tint = Blue900,
            modifier = Modifier.size(124.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.biometric_title),
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.biometric_subtitle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryButton(id = R.string.biometric_enable_btn) {
            showBiometricView = true
        }
        Spacer(modifier = Modifier.height(16.dp))
        SecondaryButton(id = R.string.biometric_skip_btn) {
            navController.navigate(Routes.RecoveryPhrase.route)
        }

        if (showBiometricView) {
            ShowBiometricPrompt(
                onAuthSuccess = {
                    biometricService.saveAuthenticationStatus(true)
                    navController.navigate(Routes.RecoveryPhrase.route)
                },
                onAuthError = { errorMessage ->
                    Log.d("BiometricView", "Failed to open biometric: $errorMessage")
                },
                onBiometricCall = { prompt, info ->
                    prompt.authenticate(info)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiometricPreview() {
    MyTonWalletTheme {
        Biometric(
            rememberNavController(),
            BiometricService(LocalContext.current)
        )
    }
}