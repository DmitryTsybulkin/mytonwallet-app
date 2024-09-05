package com.app.mytonwallet.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.mytonwallet.R
import com.app.mytonwallet.ui.walletCards.StakingBalanceCard
import com.app.mytonwallet.ui.components.NavBarItems
import com.app.mytonwallet.ui.components.Sticker
import com.app.mytonwallet.ui.walletCards.WalletDivider
import com.app.mytonwallet.ui.walletCards.transactionReceived
import com.app.mytonwallet.ui.theme.Blue900
import com.app.mytonwallet.ui.theme.Blue900_15
import com.app.mytonwallet.ui.theme.Grey300
import com.app.mytonwallet.ui.theme.Grey800
import com.app.mytonwallet.ui.theme.RobotoFontFamily
import com.app.mytonwallet.ui.theme.topAppBarColors
import com.app.mytonwallet.ui.txSheets.BottomSheet
import com.app.mytonwallet.ui.walletCards.TransactionCard
import com.app.mytonwallet.ui.walletCards.TransactionType
import com.app.mytonwallet.ui.walletCards.transactionSent
import com.app.mytonwallet.ui.walletCards.transactionSentNFT
import com.app.mytonwallet.ui.walletCards.transactionSwap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Wallet(navController: NavHostController) {
    var showSheet by remember { mutableStateOf(TransactionType.None) }
    var showEmptyWallet by remember { mutableStateOf(true) }

    when (showSheet) {
        TransactionType.Sent -> BottomSheet (tx = transactionSent) { showSheet = TransactionType.None }
        TransactionType.Received -> BottomSheet (tx = transactionReceived) { showSheet = TransactionType.None }
        TransactionType.Swapped -> BottomSheet (tx = transactionSwap) { showSheet = TransactionType.None }
        TransactionType.SentNFT -> BottomSheet (tx = transactionSentNFT) { showSheet = TransactionType.None }
        TransactionType.ReceivedNFT -> BottomSheet (tx = transactionSentNFT) { showSheet = TransactionType.None }
        TransactionType.None -> showSheet = TransactionType.None
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { showEmptyWallet = !showEmptyWallet }) {
                        Image(
                            painterResource(R.drawable.ic_sliders),
                            colorFilter = ColorFilter.tint(Grey800),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painterResource(R.drawable.ic_scan),
                            colorFilter = ColorFilter.tint(Grey800),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = topAppBarColors
            )
        },
        bottomBar = {
            NavigationBar (
                containerColor = Color.White,
                modifier = Modifier
                    .drawBehind {
                        drawLine(
                            color = Grey300,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 0.5f
                        )
                    }
            ) {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                NavBarItems.BarItems.forEach { navItem ->
                    NavigationBarItem(
                        colors = NavigationBarItemColors(
                            selectedIconColor = Blue900,
                            selectedTextColor = Blue900,
                            selectedIndicatorColor = Blue900_15,
                            unselectedIconColor = Grey800,
                            unselectedTextColor = Grey800,
                            disabledIconColor = Grey300,
                            disabledTextColor = Grey300
                        ),
                        selected = currentRoute == navItem.route,
                        onClick = {},
                        icon = {
                            Icon(
                                painter = painterResource(id = navItem.imageResource),
                                contentDescription = navItem.title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = navItem.title,
                                color = Grey800,
                                fontFamily = RobotoFontFamily,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.5.sp,
                                fontWeight = if (currentRoute == navItem.route) FontWeight.W600
                                    else FontWeight.Medium,
                            )
                        }
                    )
                }
            }
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                fontFamily = RobotoFontFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 28.sp,
                color = Grey800,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\$0",
                fontFamily = RobotoFontFamily,
                fontSize = 36.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 44.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .height(44.dp)
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {},
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = Blue900, shape = CircleShape)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_plus),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Add",
                        color = Blue900,
                        fontFamily = RobotoFontFamily,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {},
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = Blue900, shape = CircleShape)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_arrow_up),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Send",
                        color = Blue900,
                        fontFamily = RobotoFontFamily,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {},
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = Blue900, shape = CircleShape)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_stack),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Earn",
                        color = Blue900,
                        fontFamily = RobotoFontFamily,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = {},
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .size(32.dp)
                            .background(color = Blue900, shape = CircleShape)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_arrows),
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Swap",
                        color = Blue900,
                        fontFamily = RobotoFontFamily,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            WalletDivider()
            if (showEmptyWallet) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    Sticker(resource = R.raw.chick, 1)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildString {
                            append("You have no transactions")
                            append('\n')
                            append("in this wallet yet.")
                        },
                        textAlign = TextAlign.Center,
                        fontFamily = RobotoFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                }
            } else {
                Column (
                    verticalArrangement = Arrangement.Top,
                ) {
                    StakingBalanceCard()
                    WalletDivider()
                    TransactionCard(transactionReceived) {
                        showSheet = TransactionType.Received
                    }
                    TransactionCard(transactionSent) {
                        showSheet = TransactionType.Sent
                    }
                    TransactionCard(transactionSentNFT) {
                        showSheet = TransactionType.SentNFT
                    }
                    TransactionCard(transactionSwap) {
                        showSheet = TransactionType.Swapped
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletPreview() {
    var showSheet by remember { mutableStateOf(true) }

    MaterialTheme {
        Wallet(rememberNavController())
//        BottomSheet { showSheet = false }
    }
}