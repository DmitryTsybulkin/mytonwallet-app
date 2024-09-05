package com.app.mytonwallet

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.mytonwallet.services.BiometricService
import com.app.mytonwallet.services.NativeStorage
import com.app.mytonwallet.services.NotSecuredStorage
import com.app.mytonwallet.ui.components.WebViewPage
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.views.AddWallet
import com.app.mytonwallet.ui.views.Biometric
import com.app.mytonwallet.ui.views.ConfirmPasscode
import com.app.mytonwallet.ui.views.ImportWallet
import com.app.mytonwallet.ui.views.PasscodeLock
import com.app.mytonwallet.ui.views.RecoveryPhrase
import com.app.mytonwallet.ui.views.Send
import com.app.mytonwallet.ui.views.SetPasscode
import com.app.mytonwallet.ui.views.TestPhrase
import com.app.mytonwallet.ui.views.Wallet
import com.app.mytonwallet.ui.views.WalletCreated
import com.app.mytonwallet.viewmodels.ImportWalletVM
import com.app.mytonwallet.web.GetMnemonicWordListMsg
import com.app.mytonwallet.web.Message
import com.app.mytonwallet.web.ValidateMnemonicMsg
import com.app.mytonwallet.web.WebAppInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.ton.lite.client.LiteClient
import org.ton.mnemonic.Mnemonic

class MainActivity : FragmentActivity() {

    val liteClient = LiteClient(Dispatchers.Default, TESTNET_CONFIG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            var webView by remember { mutableStateOf<WebView?>(null) }
            MyApp(
//                onWebViewReady = { webView = it },
//                webView = webView
            )
        }
    }



    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
//    fun MyApp(onWebViewReady: (WebView) -> Unit, webView: WebView?) {
    fun MyApp() {
        val scope = rememberCoroutineScope()
        val biometricService = BiometricService(this)
        val keysStorage = NotSecuredStorage(this)

        val savedPassword = keysStorage.getString(Constants.PASSWORD)
        val publicKey = keysStorage.getString(Constants.PUBLIC_KEY_KEY)

        val startScreen: Routes = if (!publicKey.isNullOrBlank()) {
            if (savedPassword.isNullOrBlank()) {
                Routes.Wallet
            } else {
                Routes.PasscodeLock
            }
        } else {
            Routes.AddWallet
        }

        var mnemonic = remember { emptyList<String>().toMutableStateList() }

        LaunchedEffect(Unit) {
            if (publicKey.isNullOrBlank()) {
                mnemonic = withContext(Dispatchers.IO) {
                    Mnemonic.generate().toMutableStateList()
                }
            } else {
                // todo request transactions
            }
        }

        MyTonWalletTheme {
            Column (
                modifier = Modifier.fillMaxSize()
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startScreen.route) {
                    composable(Routes.AddWallet.route) { AddWallet(navController) }
                    composable(Routes.PasscodeLock.route) {
                        PasscodeLock(navController, biometricService, savedPassword!!)
                    }
                    composable(Routes.WalletCreated.route) {
                        WalletCreated(navController)
                    }
                    composable(Routes.SetPasscode.route) { SetPasscode(navController) }

                    composable(
                        route = Routes.ConfirmPasscode.route + "/{code}",
                        arguments = listOf(navArgument("code") {
                            type = NavType.StringType
                        })
                    ) { navBackStackEntry ->
                        ConfirmPasscode(
                            navController = navController,
                            keysStorage,
                            code = navBackStackEntry.arguments?.getString("code")
                        )
                    }

                    composable(Routes.RecoveryPhrase.route) {
                        RecoveryPhrase(navController, mnemonic)
                    }

                    composable(route = Routes.TestPhrase.route) {
                        TestPhrase(navController, keysStorage, mnemonic)
                    }

                    composable(Routes.Biometric.route) {
                        Biometric(navController, biometricService)
                    }
                    composable(Routes.ImportWallet.route) { ImportWallet(navController, keysStorage) }
                    composable(Routes.Wallet.route) { Wallet(navController) }
                    composable(Routes.Send.route) { Send(navController) }
                }
            }
        }
    }
}

@Composable
fun buildWebWorker(ctx: Context, onWebViewReady: (WebView) -> Unit, importWalletVM: ImportWalletVM) {
    val storage = NativeStorage(ctx)
    val handleMessage = { msg: Message ->
        when (msg) {
            is GetMnemonicWordListMsg -> importWalletVM.onDefaultWords(msg)
            is ValidateMnemonicMsg -> importWalletVM.onValidateMnemonic(msg)
        }
    }

    val webAppInterface = WebAppInterface(handleMessage)
    WebViewPage("file:///android_asset/index.html", onWebViewReady, webAppInterface, storage)
}

sealed class Routes(val route: String) {
    data object AddWallet : Routes("addWallet")
    data object WalletCreated : Routes("walletCreated")
    data object RecoveryPhrase : Routes("recoveryPhrase")
    data object SetPasscode : Routes("setPasscode")
    data object ConfirmPasscode : Routes("confirmPasscode")

    data object Biometric : Routes("biometric")
    data object ImportWallet : Routes("importWallet")
    data object PasscodeLock : Routes("passcodeLock")
    data object Wallet : Routes("wallet")
    data object Send : Routes("send")
    data object TestPhrase : Routes("testPhrase")
}