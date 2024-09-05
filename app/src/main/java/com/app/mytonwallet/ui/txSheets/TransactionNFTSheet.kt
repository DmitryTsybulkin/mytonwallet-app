package com.app.mytonwallet.ui.txSheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.walletCards.Transaction
import com.app.mytonwallet.ui.walletCards.TransactionType
import com.app.mytonwallet.ui.walletCards.WalletDivider
import com.app.mytonwallet.ui.walletCards.transactionSent
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun TransactionNFTSheet(tx: Transaction) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dave),
                contentDescription = "nft",
                modifier = Modifier.size(72.dp).clip(RoundedCornerShape(12.dp))
            )
            Column (
                modifier = Modifier.width(252.dp)
            ) {
                Text(
                    text = "Dave Mini",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = RobotoFontFamily,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Standalone NFT",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = RobotoFontFamily,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Left,
                    color = Grey800,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        WalletDivider()
        TransactionDetailsInfo()

        TransactionDetailsBlock(drawLine = true) {
            Text(
                text = stringResource(id = if (tx.type == TransactionType.ReceivedNFT)
                { R.string.transaction_received_at } else { R.string.transaction_sent_at }),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "31 Jan, 8:30",
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
        TransactionDetailsBlock(drawLine = true) {
            Text(
                text = stringResource(id = R.string.transaction_from),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cryptobot),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Crypto Bot",
                    fontFamily = RobotoFontFamily,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        }
        TransactionDetailsBlock(drawLine = false) {
            Text(
                text = stringResource(id = R.string.transaction_fee),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "0.25 TON",
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
        WalletDivider()
        TransactionDetailsBlock(drawLine = false) {
            Text(
                text = stringResource(id = R.string.transaction_explorer),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Blue900,
                modifier = Modifier
                    .clickable(
                        onClick = { /* todo */ },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionSentNFTSheetPreview() {
    MaterialTheme {
        TransactionNFTSheet(transactionSent)
    }
}