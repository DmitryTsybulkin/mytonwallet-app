package com.app.mytonwallet.ui.walletCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.theme.gradients

@Composable
fun TransactionReceivedCard(tx: Transaction) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(48.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = gradients
                        .shuffled()
                        .take(1)[0],
                    start = Offset(x = 48f / 2f, y = 0f),
                    end = Offset(x = 48f / 2f, y = 48f)
                ),
                shape = CircleShape
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pawprint_fill),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(22.dp)
        )
    }
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
                text = tx.from.take(4).plus("...").plus(tx.from.takeLast(4)),
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
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "+${tx.amountTON?.toInt().toString()} TON",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Green900
            )
            Text(
                text = "\$".plus(tx.amountCurrency?.toInt().toString()),
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Grey800
            )
        }
    }
}