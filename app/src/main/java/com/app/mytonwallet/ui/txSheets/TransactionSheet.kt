package com.app.mytonwallet.ui.txSheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.walletCards.Transaction
import com.app.mytonwallet.ui.theme.Black900_30
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.walletCards.TransactionType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(tx: Transaction, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = tx.type.text,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    lineHeight = 28.sp
                )
                IconButton(onClick = {
                    onDismiss()
                    scope.launch {
                        modalBottomSheetState.hide()
                    } }, modifier = Modifier.size(24.dp)) {
                    Image(
                        painterResource(R.drawable.ic_close),
                        colorFilter = ColorFilter.tint(Grey800),
                        contentDescription = "",
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        },
        containerColor = Color.White,
        scrimColor = Black900_30,
    ) {
        when (tx.type) {
            TransactionType.Sent -> TransactionSentSheet(tx = tx)
            TransactionType.Received -> TransactionReceivedSheet(tx = tx)
            TransactionType.Swapped -> TransactionSwappedSheet(tx = tx)
            TransactionType.SentNFT -> TransactionNFTSheet(tx = tx)
            TransactionType.ReceivedNFT -> TransactionNFTSheet(tx = tx)
            TransactionType.None -> Row{}
        }
    }
}

@Composable
fun TransactionDetailsInfo() {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = stringResource(id = R.string.transaction_details),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = RobotoFontFamily,
            lineHeight = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 16.dp, 0.dp, 8.dp)
        )
    }
}

@Composable
fun TransactionDetailsBlock(drawLine: Boolean, content: @Composable RowScope.() -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 20.dp)
            .drawBehind {
                if (drawLine) {
                    drawLine(
                        color = Grey300,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.5f
                    )
                }
            },
        content = content
    )
}