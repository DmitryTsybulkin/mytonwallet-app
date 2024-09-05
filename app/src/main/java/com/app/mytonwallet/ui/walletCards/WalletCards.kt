package com.app.mytonwallet.ui.walletCards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.mytonwallet.ui.theme.Grey100
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TransactionCard(transaction: Transaction,
                    onClick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        when (transaction.type) {
            TransactionType.Sent -> TransactionSentCard(tx = transaction)
            TransactionType.Received -> TransactionReceivedCard(tx = transaction)
            TransactionType.Swapped -> TransactionSwapCard(tx = transaction)
            TransactionType.SentNFT -> TransactionNFTCard(tx = transaction)
            TransactionType.ReceivedNFT -> TransactionNFTCard(tx = transaction)
            TransactionType.None -> Row{}
        }
    }
}

enum class TransactionType(val text: String) {
    Sent("Sent"),
    Received("Received"),
    Swapped("Swapped"),
    SentNFT("Sent NFT"),
    ReceivedNFT("Received NFT"),
    None("")
}

data class Transaction(
    val from: String,
    val to: String,
    val type: TransactionType,
    val amountTON: BigDecimal?,
    val amountCurrency: BigDecimal?,
    val datetime: Date
)
var timeFormat: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

val transactionSent = Transaction(
    from = "EQDtFpEwcFAEcRe5mLVh2N6C0x-_hJEM7W61_JLnSF74p4q2",
    to = "UQCnqvBl3oDx7LgSiYwD2XO6q1iuP5-PrjIGKH_de-qjOlcB",
    type = TransactionType.Sent,
    amountTON = BigDecimal.valueOf(1.0),
    amountCurrency = BigDecimal.valueOf(5.0),
    datetime = Date()
)

val transactionReceived = Transaction(
    from = "UQCnqvBl3oDx7LgSiYwD2XO6q1iuP5-PrjIGKH_de-qjOlcB",
    to = "EQDtFpEwcFAEcRe5mLVh2N6C0x-_hJEM7W61_JLnSF74p4q2",
    type = TransactionType.Received,
    amountTON = BigDecimal.valueOf(3.0),
    amountCurrency = BigDecimal.valueOf(15.0),
    datetime = Date()
)

val transactionSentNFT = Transaction(
    from = "UQCnqvBl3oDx7LgSiYwD2XO6q1iuP5-PrjIGKH_de-qjOlcB",
    to = "EQDtFpEwcFAEcRe5mLVh2N6C0x-_hJEM7W61_JLnSF74p4q2",
    type = TransactionType.SentNFT,
    amountTON = null,
    amountCurrency = null,
    datetime = Date()
)

val transactionSwap = Transaction(
    from = "",
    to = "",
    type = TransactionType.Swapped,
    amountTON = BigDecimal.valueOf(3.0),
    amountCurrency = BigDecimal.valueOf(10.0),
    datetime = Date()
)

@Composable
fun WalletDivider() {
    Spacer(
        modifier = Modifier
            .height(12.dp)
            .fillMaxWidth()
            .background(color = Grey100)
            .drawBehind {
                drawLine(
                    color = Grey300,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 0.5f
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
fun WalletCardsPreview() {
    MyTonWalletTheme {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StakingBalanceCard()
            WalletDivider()
            TransactionSentCard(transactionSentNFT)
            TransactionReceivedCard(transactionReceived)
            TransactionNFTCard(transactionSentNFT)
            TransactionSwapCard(transactionSentNFT)
        }
    }
}