package com.app.mytonwallet.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.ui.components.GoBackIcon
import com.app.mytonwallet.ui.components.SecondaryButton
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.Black500_36
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.topAppBarColors

@ExperimentalComposeUiApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPasscode(navController: NavHostController) {
    var size by remember { mutableIntStateOf(4) }
    val code = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

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
                value = code.value,
                onValueChange = { s ->
                    code.value = s
                    if (code.value.length == size) {
                        navController
                            .navigate(Routes.ConfirmPasscode.route + "/${code.value}")
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
                text = stringResource(id = R.string.set_passcode_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.set_passcode_subtitle, size),
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
                modifier = Modifier.padding(8.dp)
            ) {
                items(
                    count = size,
                    itemContent = { i ->
                        SecretPoint(i, code)
                    }
                )
            }
            Spacer(modifier = Modifier.height(112.dp))
            SecondaryButton(id = if (size == 4)
                R.string.set_passcode_6_btn else R.string.set_passcode_4_btn) {
                size = if (size == 4) 6 else 4
            }
        }
    }
}

@Composable
fun SecretPoint(i: Int, code: MutableState<String>) {
    val style: DrawStyle
    val color: Color
    if (code.value.length > i) {
        color = Color.Black
        style = Fill
    } else {
        color = Black500_36
        style = Stroke(width = 1.dp.value)
    }
    Canvas(
        modifier = Modifier.size(16.dp)
    ) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            style = style
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun SetPasscodePreview() {
    MyTonWalletTheme {
        SetPasscode(rememberNavController())
    }
}