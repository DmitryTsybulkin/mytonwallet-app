package com.app.mytonwallet.ui.views

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
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
import com.app.mytonwallet.ui.components.NumButton
import com.app.mytonwallet.ui.components.ShowBiometricPrompt
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.White_36

@Composable
fun PasscodeLock(
    navController: NavHostController,
    biometricService: BiometricService,
    savedPassword: String,
) {
    val code = remember { mutableStateOf("") }
    var showBiometricView by remember {
        mutableStateOf(false)
    }

    var triggerAnimation by remember { mutableStateOf(false) }
    val shakeAnimation = remember { Animatable(0f) }

    LaunchedEffect(triggerAnimation) {
        if (triggerAnimation) {
            shakeAnimation.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 400
                    for (i in 1..6) {
                        when (i) {
                            1 -> 10f at 50 * i using LinearOutSlowInEasing
                            2 -> (-10f) at 50 * i using LinearOutSlowInEasing
                            3 -> 8f at 50 * i using LinearOutSlowInEasing
                            4 -> (-8f) at 50 * i using LinearOutSlowInEasing
                            5 -> 4f at 50 * i using LinearOutSlowInEasing
                            6 -> 0f at 50 * i using LinearOutSlowInEasing
                        }
                    }
                }
            )
            triggerAnimation = false
        }
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue900)
            .padding(horizontal = 46.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Lock,
            tint = Color.White,
            contentDescription = "Lock icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier.fillMaxWidth().offset(x = shakeAnimation.value.dp)
        ) {
            items(
                count = savedPassword.length,
                itemContent = { i ->
                    SecretDot(i, code)
                }
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(id = R.string.passcode_lock_title),
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(id = R.string.passcode_lock_subtitle),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
        KeyBoardNumbers(
            onNumClick = { strNum ->
                if (strNum == "") {
                    code.value = code.value.dropLast(1)
                }
                if (code.value.length < savedPassword.length) {
                    code.value = code.value.plus(strNum)
                }
                if (code.value.length == savedPassword.length) {
                    if (code.value == savedPassword) {
                        navController.navigate(Routes.Wallet.route)
                    } else {
                        triggerAnimation = true
                    }
                }
            },
            onFingerClick = {
                showBiometricView = true
            }
        )
    }

    if (showBiometricView) {
        ShowBiometricPrompt(
            onAuthSuccess = {
                biometricService.saveAuthenticationStatus(true)
                navController.navigate(Routes.Wallet.route)
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

@Composable
fun SecretDot(i: Int, code: MutableState<String>) {
    val style: DrawStyle
    val color: Color
    if (code.value.length > i) {
        color = Color.White
        style = Fill
    } else {
        color = White_36
        style = Stroke(width = 1.dp.value)
    }
    Canvas(modifier = Modifier.size(16.dp)) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            style = style
        )
    }
}

@Composable
fun KeyBoardNumbers(onNumClick: (String) -> Unit, onFingerClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        NumRow {
            NumButton(onClick = { onNumClick("1") }, text = "1")
            NumButton(onClick = { onNumClick("2") }, text = "2", label = "ABC")
            NumButton(onClick = { onNumClick("3") }, text = "3", label = "DEF")
        }
        NumRow {
            NumButton(onClick = { onNumClick("4") }, text = "4", label = "GHI")
            NumButton(onClick = { onNumClick("5") }, text = "5", label = "JKL")
            NumButton(onClick = { onNumClick("6") }, text = "6", label = "MNO")
        }
        NumRow {
            NumButton(onClick = { onNumClick("7") }, text = "7", label = "PQRS")
            NumButton(onClick = { onNumClick("8") }, text = "8", label = "TUV")
            NumButton(onClick = { onNumClick("9") }, text = "9", label = "WXYZ")
        }
        NumRow {
            IconButton(
                onClick = { onFingerClick() },
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_fingerprint),
                    contentDescription = "Fingerprint",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            NumButton(onClick = { onNumClick("0") }, text = "0")
            IconButton(
                onClick = { onNumClick("") },
                modifier = Modifier.size(80.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_delete),
                    contentDescription = "Clear",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun NumRow(content: @Composable RowScope.() -> Unit) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Blue900),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun LockScreenPreview() {
    MyTonWalletTheme {
        PasscodeLock(rememberNavController(), BiometricService(LocalContext.current), "1234")
    }
}