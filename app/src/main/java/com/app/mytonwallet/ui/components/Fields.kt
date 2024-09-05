package com.app.mytonwallet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.mytonwallet.ui.theme.Black900_10
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Grey100
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.Red900
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.viewmodels.ImportWalletVM

@Composable
fun DefaultWordInput(
    label: Int,
    word: String,
    onValueChange: (String) -> Unit = {},
) {
    Box {
        TextField(
            value = word,
            onValueChange = { s ->
                onValueChange(s)
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Grey100,
                focusedContainerColor = Grey100,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorContainerColor = Grey100,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Blue900,
            ),
            textStyle = TextStyle(
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Start,
            ),
            leadingIcon = {
                Text(
                    text = label.toString(),
                    color = Grey800,
                    textAlign = TextAlign.Right,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    modifier = Modifier.size(width = 24.dp, height = 20.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Composable
fun WordInput(
    label: Int,
    index: Int,
    vm: ImportWalletVM
) {
    var showContextMenu by remember { mutableStateOf(true) }
    val searchResults by vm.searchResults.collectAsStateWithLifecycle(listOf("test1"))
    var isError by remember { mutableStateOf(false) }

    Box {
        TextField(
            value = vm.words[index],
            onValueChange = { s ->
                vm.words[index] = s
                showContextMenu = true
                vm.onSearchQueryChange(s)
                isError = !searchResults.contains(s)
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Grey100,
                focusedContainerColor = Grey100,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorContainerColor = Grey100,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Blue900,
            ),
            textStyle = TextStyle(
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
                color = if (isError) { Red900 } else { Color.Black },
                textAlign = TextAlign.Start,
            ),
            leadingIcon = {
                Text(
                    text = label.toString(),
                    color = Grey800,
                    textAlign = TextAlign.Right,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    modifier = Modifier.size(width = 24.dp, height = 20.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .onFocusChanged {
                    showContextMenu = false
                }
        )
        if (showContextMenu) {
            ContextWordMenu(searchResults) { s ->
                vm.words[index] = s
                isError = false
                showContextMenu = false
            }
        }
    }
}

@Composable
fun ContextWordMenu(
    searchResults: List<String>,
    onClick: (String) -> Unit
) {
    LazyRow (
        modifier = Modifier
            .absoluteOffset(x = 36.dp, y = (-56).dp)
            .sizeIn(maxWidth = 246.dp)
            .background(color = Color.White)
            .border(width = 0.5.dp, color = Black900_10, shape = RoundedCornerShape(6.dp))
    ) {
        items(
            count = if (searchResults.size > 4) { 4 } else { searchResults.size },
            itemContent = { i ->
                val word = searchResults[i]
                Text(
                    text = word,
                    fontFamily = RobotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .clickable (
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onClick(word)
                        }
                )
            }
        )
    }
//    Box (
//        modifier = Modifier
//            .zIndex(1f)
//            .absoluteOffset(x = 36.dp, y = (-56).dp)
//            .sizeIn(maxWidth = 246.dp)
//            .shadow(
//                elevation = 2.dp,
//                shape = RoundedCornerShape(6.dp),
//                spotColor = Black900_10
//            )
//    ) {

//    }
}

@Preview(showBackground = true)
@Composable
fun WordInputPreview() {
    val viewModel = ImportWalletVM()
    MaterialTheme {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            WordInput(label = 1, index = 0, vm = viewModel)
            WordInput(label = 2, index = 1, vm = viewModel)
            WordInput(label = 3, index = 2, vm = viewModel)
        }
    }
}