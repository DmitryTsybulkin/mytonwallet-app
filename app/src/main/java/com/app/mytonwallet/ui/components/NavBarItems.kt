package com.app.mytonwallet.ui.components

import com.app.mytonwallet.R

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Wallet",
            imageResource = R.drawable.ic_wallet,
            route = "wallet"
        ),
        BarItem(
            title = "Assets",
            imageResource = R.drawable.ic_grid,
            route = "assets"
        ),
        BarItem(
            title = "Browser",
            imageResource = R.drawable.ic_compass,
            route = "browser"
        ),
        BarItem(
            title = "Settings",
            imageResource = R.drawable.ic_settings,
            route = "settings"
        )
    )
}

data class BarItem(
    val title: String,
    val imageResource: Int,
    val route: String
)