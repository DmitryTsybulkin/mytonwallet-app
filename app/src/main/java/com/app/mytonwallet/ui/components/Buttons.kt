package com.app.mytonwallet.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.theme.Blue500
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.MyTonWalletTheme
import com.app.mytonwallet.ui.theme.White_66

@Composable
fun PrimaryButton(@StringRes id: Int, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Blue900,
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = stringResource(id = id),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun SecondaryButton(@StringRes id: Int, onClick: (Int) -> Unit = {}) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Blue900)) {
                    append(stringResource(id = id))
                }
            },
            style = MaterialTheme.typography.headlineLarge,
            onClick = onClick
        )
    }
}

@Composable
fun NumButton(onClick: () -> Unit = {}, text: String, label: String = "", color: Color = Blue500) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(80.dp)
            .background(color = color, shape = CircleShape)
            .clip(CircleShape)
            .clickable { onClick() }
    ) {
        Text(text = text, color = Color.White, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, color = White_66, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelMedium, modifier = Modifier.fillMaxWidth().height(height = 20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    MyTonWalletTheme {
        Column (
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(id = R.string.app_name)
            SecondaryButton(id = R.string.app_name)
            NumButton(text = "2", label = "PQRS")
        }
    }
}