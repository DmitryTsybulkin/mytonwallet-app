package com.app.mytonwallet.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.theme.White_88
import com.app.mytonwallet.ui.theme.topAppBarColors
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Send(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.send_title),
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        fontFamily = RobotoFontFamily
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Image(
                            painterResource(R.drawable.ic_close),
                            colorFilter = ColorFilter.tint(Grey800),
                            contentDescription = "",
                            modifier = Modifier.size(14.dp)
                        )
                    }
                },
                modifier = Modifier
                    .background(color = White_88)
                    .drawBehind {
                        drawLine(
                            color = Grey300,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 0.5f
                        )
                    },
                colors = topAppBarColors
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.send_subtitle_currency),
                    modifier = Modifier.padding(20.dp, 16.dp, 20.dp, 8.dp),
                    fontFamily = RobotoFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp
                )
            }
            Row (
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth()
            ) {
                AssetBlock()
            }
        }
    }
}

data class Asset(
    val title: String,
    val imageResource: Int,
    val currency: String,
    val balance: BigDecimal
)

val tether = Asset(
    title = "Tether USD",
    imageResource = R.drawable.tether,
    currency = "USD₮",
    balance = BigDecimal(10_527)
)

@Composable
fun AssetBlock() {
    val ass by remember { mutableStateOf(tether) }
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.setGroupingSeparator(' '); // Set the grouping separator to a space
    val decimalFormat = DecimalFormat("#,###", symbols)
    val arr = arrayOf(1, 2, 3)


    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = ass.imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
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
                // todo: if it's box on the left, it's 10
                .padding(vertical = 10.dp)
        ) {
            Column {
                Text(text = ass.title, fontWeight = FontWeight.Bold)
                Text(
                    text = decimalFormat.format(ass.balance).plus(" ").plus(ass.currency),
                    color = Grey800,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendPreview() {
    MyTonWalletTheme {
        Send(rememberNavController())
    }
}