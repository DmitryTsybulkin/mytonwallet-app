package com.app.mytonwallet.ui.components

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.theme.gradients

@Composable
fun Sticker(@RawRes resource: Int, repeat: Int = LottieConstants.IterateForever) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))
    val progress by animateLottieCompositionAsState(composition, iterations = repeat)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(width = 124.dp, height = 124.dp)
    )
}

@Composable
fun GoBackIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_back_arrow),
        contentDescription = "Menu",
        tint = Grey800
    )
}

enum class IcoSize(val diameter: Dp, val fontSize: TextUnit, val lineHeight: TextUnit) {
    Big(48.dp, 22.sp, 28.sp),
    Small(24.dp, 12.sp, 14.sp)
}

@Composable
fun UserIcon(letter: String = "A", size: IcoSize) {
    val colors = remember { gradients.shuffled().take(1)[0].toMutableStateList() }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(size.diameter)
            .background(
                brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(x = size.diameter.value / 2f, y = 0f),
                    end = Offset(x = size.diameter.value / 2f, y = size.diameter.value)
                ),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
    ) {
        Text(
            text = letter,
            fontFamily = RobotoFontFamily,
            fontSize = size.fontSize,
            lineHeight = size.lineHeight,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}