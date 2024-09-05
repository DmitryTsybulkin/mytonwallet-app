package com.app.mytonwallet.ui.views

import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.Constants
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.Utils
import com.app.mytonwallet.services.NotSecuredStorage
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.WordInput
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.theme.topAppBarColors
import com.app.mytonwallet.viewmodels.ImportWalletVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportWallet(navController: NavHostController,
                 storage: NotSecuredStorage,
                 vm: ImportWalletVM = viewModel()) {
    val showDialog = remember { mutableStateOf(false) }

//    LaunchedEffect(mnemonic) {
//        if (mnemonic.isEmpty()) {
//            webView?.evaluateJavascript("(async function() { return await generateMnemonic(); })();") {}
//        }
//        scope.launch {
//            webView?.evaluateJavascript("(async function() { return await getMnemonicWordList(); })();") {}
//        }
//    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton({ navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "Menu",
                            tint = Grey800
                        )
                    }
                },
                colors = topAppBarColors)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(state = ScrollState(initial = 0))
                .padding(it)
                .padding(horizontal = 48.dp)

        ) {
            if (showDialog.value) {
                WrongPhraseAlert(showDialog)
            }
            Text(text = stringResource(id = R.string.import_wallet_title), style = MaterialTheme.typography.displayMedium)
            Spacer(Modifier.height(12.dp))
            Text(
                text = buildAnnotatedString {
                    append("You can restore access to your wallet by entering the ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
                        append("24 secret words")
                    }
                    append(" that you wrote down when creating the wallet.")
                },
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
                modifier = Modifier.heightIn(max = (48 * 24 + (16 * 23)).dp),
            ) {
                items(
                    count = 24,
                    itemContent = { i ->
                        WordInput (
                            label = i+1,
                            index = i,
                            vm = vm
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryButton(id = R.string.test_btn) {
                if (vm.validMnemonic()) {
                    val pair = Utils.toKeyPair(vm.words.toList(), "")
                    storage.save(Constants.PUBLIC_KEY_KEY, pair.first)
                    storage.save(Constants.PRIVATE_KEY_KEY, pair.second)
                    navController.navigate(Routes.Wallet.route)
                } else {
                    showDialog.value = true
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrongPhraseAlert(isWrongPhrase: MutableState<Boolean>) {
    if (isWrongPhrase.value) {
        BasicAlertDialog(
            onDismissRequest = {
                isWrongPhrase.value = false
            },
            modifier = Modifier
                .width(316.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White),
        ) {
            Column {
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.import_wallet_dialog_title),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = stringResource(id = R.string.import_wallet_dialog_subtitle))
                }

                Column (
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(12.dp, 10.dp, 12.dp, 10.dp)
                                .clickable { isWrongPhrase.value = false }
                        ) {
                            Text(
                                text = stringResource(id = R.string.import_wallet_dialog_btn),
                                fontWeight = FontWeight.Medium,
                                fontFamily = RobotoFontFamily,
                                color = Blue900,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
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
fun ImportWalletPreview() {
    val alert = remember { mutableStateOf(true) }
    MyTonWalletTheme {
        ImportWallet(rememberNavController(), NotSecuredStorage(LocalContext.current))
//        WrongPhraseAlert(alert)
    }
}