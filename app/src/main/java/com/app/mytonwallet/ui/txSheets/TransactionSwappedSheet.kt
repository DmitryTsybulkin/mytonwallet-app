package com.app.mytonwallet.ui.txSheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.walletCards.Transaction
import com.app.mytonwallet.ui.walletCards.WalletDivider
import com.app.mytonwallet.ui.walletCards.transactionSent
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Green800
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun TransactionSwappedSheet(tx: Transaction) {
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
            Box (
                modifier = Modifier.size(64.dp, 72.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ton_symbol),
                    contentDescription = "ton",
                    modifier = Modifier.size(48.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.mytonwallet),
                    contentDescription = "mytonwallet",
                    modifier = Modifier
                        .size(48.dp)
                        .offset(16.dp, 24.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color.White,
                                radius = size.width / 2 + 3.dp.toPx(),
                            )
                        }
                )
            }
            Column (
                Modifier.width(300.dp)
            ) {
                Text(
                    text = "-100 TON",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = RobotoFontFamily,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Left,
                    overflow = TextOverflow.Ellipsis, // todo everywhere
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "+1000 MY",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = RobotoFontFamily,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Left,
                    color = Green800,
                    modifier = Modifier.fillMaxWidth()
                )
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
                text = stringResource(id = R.string.transaction_swapped),
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
                text = stringResource(id = R.string.transaction_swapped_sent),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "100 TON",
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
                text = stringResource(id = R.string.transaction_swapped_received),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "1 000 MY",
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
                text = stringResource(id = R.string.transaction_swapped_price, "MY"),
                fontFamily = RobotoFontFamily,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Grey800
            )
            Text(
                text = "0.1 TON",
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
fun TransactionSwappedSheetPreview() {
    MaterialTheme {
        TransactionSwappedSheet(transactionSent)
    }
}