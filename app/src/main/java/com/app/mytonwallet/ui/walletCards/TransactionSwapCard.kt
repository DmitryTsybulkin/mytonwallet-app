package com.app.mytonwallet.ui.walletCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun TransactionSwapCard(tx: Transaction) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(48.dp)
    ) {
        Box (
            modifier = Modifier.size(48.dp, 48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mytonwallet),
                contentDescription = "ton",
                modifier = Modifier.size(32.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ton_symbol),
                contentDescription = "mytonwallet",
                modifier = Modifier
                    .size(32.dp)
                    .offset(16.dp, 16.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.White,
                            radius = size.width / 2 + 3.dp.toPx(),
                        )
                    }
            )
        }
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
            val arrow = painterResource(id = R.drawable.ic_text_arrow)
            val inlineContent = mapOf(
                "inlineArrows" to InlineTextContent(
                    Placeholder(
                        width = 6.sp,
                        height = 12.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Image(
                        painter = arrow,
                        contentDescription = "Vector Image",
                        modifier = Modifier.size(width = 7.dp, height = 30.dp),
                        colorFilter = ColorFilter.tint(Grey800)
                    )
                }
            )
            Text(
                text = buildAnnotatedString {
                    append("MY ")
                    appendInlineContent("inlineArrows", "[icon]")
                    appendInlineContent("inlineArrows", "[icon]")
                    append(" TON")
                },
                inlineContent = inlineContent,
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
                text = "-100 MY",
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Grey800
            )
        }
    }
}