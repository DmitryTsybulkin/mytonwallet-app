package com.app.mytonwallet.ui.txSheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.components.EncryptedField
import com.app.mytonwallet.ui.components.IcoSize
import com.app.mytonwallet.ui.walletCards.Transaction
import com.app.mytonwallet.ui.components.UserIcon
import com.app.mytonwallet.ui.walletCards.WalletDivider
import com.app.mytonwallet.ui.walletCards.transactionSent
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun TransactionSentSheet(tx: Transaction) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(space = 8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Text(
                text = "-500 TON",
                fontSize = 36.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = RobotoFontFamily,
                lineHeight = 44.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "\$1 234",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = RobotoFontFamily,
                lineHeight = 28.sp,
                color = Grey800,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            if ("have encrypted message" == "have encrypted message") {
                EncryptedField(tx)
            }
        }
        WalletDivider()
        TransactionDetailsInfo()

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 20.dp)
                .drawBehind {
                    drawLine(
                        color = Grey300,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.5f
                    )
                }
        ) {
            Text(
                text = stringResource(id = R.string.transaction_to),
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
                UserIcon("A", IcoSize.Small)
                Text(
                    text = "alice.ton",
                    fontFamily = RobotoFontFamily,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 20.dp)
                .drawBehind {
                    drawLine(
                        color = Grey300,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.5f
                    )
                }
        ) {
            Text(
                text = stringResource(id = R.string.transaction_amount),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "500 TON",
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 20.dp)
        ) {
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
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 20.dp)
        ) {
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
fun TransactionSentSheetPreview() {
    MaterialTheme {
        TransactionSentSheet(transactionSent)
    }
}