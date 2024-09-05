package com.app.mytonwallet.ui.views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.Constants
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.services.NotSecuredStorage
import com.app.mytonwallet.ui.components.GoBackIcon
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.topAppBarColors

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasscode(navController: NavHostController,
                    keysStorage: NotSecuredStorage,
                    code: String?) {
    val confirmCode = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

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

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton({ navController.navigateUp() }) {
                        GoBackIcon()
                    }
                },
                colors = topAppBarColors
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .padding(48.dp, 47.dp, 48.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = confirmCode.value,
                onValueChange = { s ->
                    if (confirmCode.value.length < code!!.length || s.length < confirmCode.value.length) {
                        confirmCode.value = s
                    }
                    if (confirmCode.value.length == code.length) {
                        if (confirmCode.value == code) {
                            keysStorage.save(Constants.PASSWORD, code)
                            navController.navigate(Routes.Biometric.route)
                        } else {
                            triggerAnimation = true
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.NumberPassword
                ),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .alpha(0f)
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Sticker(resource = R.raw.monkey, 1)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.confirm_passcode_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.confirm_passcode_subtitle, code!!.length),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = Modifier.padding(8.dp).offset(x = shakeAnimation.value.dp)
            ) {
                items(
                    count = code.length,
                    itemContent = { i ->
                        SecretPoint(i, confirmCode)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun PasscodeConfirmPreview() {
    MyTonWalletTheme {
        ConfirmPasscode(rememberNavController(), NotSecuredStorage(LocalContext.current), "1234")
    }
}