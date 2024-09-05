package com.app.mytonwallet.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.Constants
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.Utils
import com.app.mytonwallet.services.NotSecuredStorage
import com.app.mytonwallet.ui.components.DefaultWordInput
import com.app.mytonwallet.ui.components.GoBackIcon
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestPhrase(
    navController: NavHostController,
    storage: NotSecuredStorage,
    mnemonic: List<String>?
) {
    val words = remember { List(24) { "" }.toMutableStateList() }
    val isWrongPhrase = remember { mutableStateOf(false) }
    val rndIndexes = (0 until 24).shuffled().take(3)

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
                .fillMaxSize()
                .padding(48.dp, 83.dp, 48.dp)
        ) {
            WrongWordsAlert(isWrongWords = isWrongPhrase, navController)
            Sticker(resource = R.raw.teacher, 1)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.test_title),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = buildAnnotatedString {
                    append("Let’s check that you wrote it down correctly. Please enter the words ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("4")
                    }
                    append(",")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append(" 12 ")
                    }
                    append("and ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("19")
                    }
                    append(".")
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                modifier = Modifier.heightIn(max = (48 * 3 + (16 * 2)).dp)
            ) {
                items(
                    count = 3,
                    itemContent = { i ->
                        DefaultWordInput(label = rndIndexes[i]+1, word = words[rndIndexes[i]]) {s ->
                            words[rndIndexes[i]] = s
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(id = R.string.test_btn) {
                mnemonic?.let {
                    if (mnemonic[rndIndexes[0]] == words[rndIndexes[0]]
                            && mnemonic[rndIndexes[1]] == words[rndIndexes[1]]
                            && mnemonic[rndIndexes[2]] == words[rndIndexes[2]]) {
                        val pair = Utils.toKeyPair(mnemonic, "")
                        storage.save(Constants.PUBLIC_KEY_KEY, pair.first)
                        storage.save(Constants.PRIVATE_KEY_KEY, pair.second)
                        navController.navigate(Routes.Wallet.route)
                    } else {
                        isWrongPhrase.value = true
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongWordsAlert(isWrongWords: MutableState<Boolean>, navController: NavHostController) {
    if (isWrongWords.value) {
        BasicAlertDialog(
            onDismissRequest = {
                isWrongWords.value = false
            },
            // todo: what is the right width? should it inherit parent width?
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .requiredWidth(316.dp)
                .background(color = Color.White),
        ) {
            Column (
                modifier = Modifier.widthIn(min = 280.dp, max = 560.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.test_dialog_title),
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.test_dialog_subtitle),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column (
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                        modifier = Modifier.padding(0.dp, 12.dp, 12.dp, 12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(12.dp, 10.dp, 12.dp, 10.dp)
                                .clickable {
                                    isWrongWords.value = false
                                    navController.navigateUp()
                                }
                        ) {
                            Text(
                                text = stringResource(id = R.string.test_dialog_secondary_btn),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF3390EC),
                            )
                        }
                        Column(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(12.dp, 10.dp, 12.dp, 10.dp)
                                .clickable { isWrongWords.value = false }
                        ) {
                            Text(
                                text = stringResource(id = R.string.test_dialog_primary_btn),
                                style = MaterialTheme.typography.titleMedium,
                                color = Blue900,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPhrasePreview() {
    val isWrongPhrase = remember { mutableStateOf(true) }
    MyTonWalletTheme {
        TestPhrase(rememberNavController(), NotSecuredStorage(LocalContext.current), listOf("wolf", "wonderful", "woman"))
//        WrongWordsAlert(isWrongWords = isWrongPhrase, navController = rememberNavController())
    }
}