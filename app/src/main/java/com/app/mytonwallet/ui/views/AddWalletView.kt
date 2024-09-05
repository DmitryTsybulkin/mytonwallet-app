package com.app.mytonwallet.ui.views

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.SecondaryButton
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import kotlinx.coroutines.launch

@Composable
fun AddWallet(navController: NavHostController) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 46.dp)
    ) {
        Sticker(R.raw.diamond)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayMedium,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.add_wallet_sub_title),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(R.string.add_wallet_create_btn) {
            navController.navigate(Routes.WalletCreated.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        SecondaryButton(R.string.add_wallet_import_btn) {
            navController.navigate(Routes.ImportWallet.route)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddWalletPreview() {
    MyTonWalletTheme {
        AddWallet(rememberNavController())
    }
}