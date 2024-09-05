package com.app.mytonwallet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Grey100
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.walletCards.Transaction

@Composable
fun EncryptedField(tx: Transaction) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Grey100, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.transaction_msg_encrypted),
            color = Grey800,
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        )
        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontFamily = RobotoFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Blue900,
                )) {
                    append("Decrypt")
                }
            }
        ) {

        }
    }
}