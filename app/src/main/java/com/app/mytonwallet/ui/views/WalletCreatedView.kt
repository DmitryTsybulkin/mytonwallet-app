package com.app.mytonwallet.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.Routes
import com.app.mytonwallet.ui.components.PrimaryButton
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.theme.MyTonWalletTheme

@Composable
fun WalletCreated(navController: NavHostController) {
    Column (
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 46.dp)
    ) {
        Sticker(R.raw.petard, 1)
        Text(text = stringResource(id = R.string.wallet_created_title), style = MaterialTheme.typography.displayMedium)
        Text(text = stringResource(id = R.string.wallet_created_subtitle), style = MaterialTheme.typography.bodySmall)
        PrimaryButton(id = R.string.wallet_created_btn) {
            navController.navigate(Routes.SetPasscode.route)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletCreatedPreview() {
    MyTonWalletTheme {
        WalletCreated(rememberNavController())
    }
}