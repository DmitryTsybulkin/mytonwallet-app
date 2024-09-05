package com.app.mytonwallet.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.ui.components.GoBackIcon
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryPhrase(
    navController: NavHostController,
    mnemonic: List<String>
) {
    val state = rememberScrollState()

    LaunchedEffect(Unit) { state.animateScrollTo(150) }

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
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 48.dp)
                .verticalScroll(state = state),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sticker(R.raw.note, 1)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.recovery_phrase_title),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 32.dp, alignment = Alignment.Top),
            ) {
                Text(
                    text = stringResource(id = R.string.recovery_phrase_subtitle),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
                var halfSize = (mnemonic.size + 1) / 2
                val firstColumn = mnemonic.take(halfSize)
                val secondColumn = mnemonic.drop(halfSize)
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        firstColumn.forEachIndexed { i, text ->
                            WordBlock(index = i + 1, word = text)
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        secondColumn.forEach { text ->
                            WordBlock(index = halfSize + 1, word = text)
                            halfSize += 1
                        }
                    }
                }
                PrimaryButton(id = R.string.recovery_phrase_btn) {
                    navController.navigate(Routes.TestPhrase.route)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

    }
}

@Composable
fun WordBlock(index: Int, word: String) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.size(width = 156.dp, height = 24.dp)
    ) {
        Text(
            text = index.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = Grey800,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .size(width = 24.dp, height = 20.dp)
                .align(Alignment.CenterVertically))
        Text(
            text = word,
            modifier = Modifier.width(width = 116.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryPhrasePreview() {
    MyTonWalletTheme {
        RecoveryPhrase(rememberNavController(), List(24) { i -> "${i}_word" })
    }
}