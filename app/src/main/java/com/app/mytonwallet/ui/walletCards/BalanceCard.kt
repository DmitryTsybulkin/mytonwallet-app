package com.app.mytonwallet.ui.walletCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Green900
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily

@Composable
fun StakingBalanceCard() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
    ) {
        StakedTON()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp)
                .drawBehind {
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
                    text = "Staked TON",
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = buildAnnotatedString {
                        append("\$8.00 ")
                        withStyle(style = SpanStyle(color = Green900)) {
                            append("APY 4%")
                        }
                    },
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
                    text = "10 527 TON",
                    color = Green900,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "\$10,527",
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Grey800
                )
            }
        }
    }
}

@Composable
fun TONBalanceCard() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ton_symbol),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Blue900)
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp)
                .drawBehind {
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
                    text = "Toncoin",
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = buildAnnotatedString {
                        append("\$8.00 ")
                        withStyle(style = SpanStyle(color = Green900)) {
                            append("+1.12%")
                        }
                    },
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
                    text = "110 TON",
                    color = Green900,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "\$880",
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Grey800
                )
            }
        }
    }
}

@Composable
fun StakedTON() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.ton_symbol),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Blue900)
        )
        Text(
            text = "%",
            fontFamily = RobotoFontFamily,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = .15.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .size(20.dp)
                .offset(x = 32.dp, y = 37.dp)
                .background(color = Green900, shape = CircleShape)
                .drawBehind {
                    drawCircle(
                        color = Color.White,
                        radius = size.width / 2,
                        style = Stroke(1.5.dp.toPx())
                    )
                }
        )
    }
}