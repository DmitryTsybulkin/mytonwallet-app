package com.app.mytonwallet.ui.walletCards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.ui.components.IcoSize
import com.app.mytonwallet.ui.components.UserIcon
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun TransactionNFTCard(tx: Transaction) {
    UserIcon(size = IcoSize.Big, letter = "A")
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp)
            .drawBehind {
                // todo: if not last
                drawLine(
                    color = Grey300,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 0.5f
                )
            }
            .padding(vertical = 10.dp)
    ) {
        Column {
            Text(
                text = "alice.ton",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            )
            Text(
                text = tx.type.text.plus(" \u00B7 ").plus(timeFormat.format(tx.datetime)),
                color = Grey800,
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = "Cat #123",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            )
            Text(
                text = "Rich Cats",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Grey800
            )
        }
        Column (
            modifier = Modifier
                .size(40.dp)
                .background(Green900, shape = RoundedCornerShape(8.dp))
        ) {}
    }
}