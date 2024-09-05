package com.app.mytonwallet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_black, weight = FontWeight.Black),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_thin, weight = FontWeight.Thin),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // Wallet balance
        fontSize = 36.sp,
        lineHeight = 44.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // title
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // dialog title/top app bar title/wallet name
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.W500, // buttons
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // main text header
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // wallet 4 buttons
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // dialog buttons
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp // depends (on import wallet = 0)
    ),
    titleSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal, // import wallet tags
        fontSize = 15.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal, // dialog main text
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.15.sp // on import wallet and tx time = 0
    ),
    bodySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal, // main text
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal, // number labels in inputs
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    labelLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal, // numpad nums
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.44).sp
    ),
    labelMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // numpad letters
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.W600, // bottom tabs (selected = semibold not = medium)
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium, // bottom tabs (selected = semibold not = medium)
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)


