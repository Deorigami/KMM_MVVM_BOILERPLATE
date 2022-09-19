
package com.eyedea.dashboard.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.eyedea.dashboard.DashboardScreenNavigator
import com.eyedea.dashboard.R
import com.eyedea.dashboard.screen.DashboardHome
import com.eyedea.dashboard.screen.DashboardLogin
import com.eyedea.dashboard.screen.DashboardOrder
import com.eyedea.dashboard.screen.DashboardWhistlist
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


enum class BottomBarItem(
    val page: @Composable (DashboardScreenNavigator) -> Unit,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    HOME({ DashboardHome(navigator = it) }, R.drawable.ic_menu_home, R.string.home_bottom_navbar),
    WISHLIST({ DashboardWhistlist()}, R.drawable.ic_menu_wishlist, R.string.wishlist_bottom_navbar),
    ORDER({ DashboardOrder()}, R.drawable.ic_menu_bag, R.string.order_bottom_navbar),
    LOGIN({ DashboardLogin()}, R.drawable.ic_menu_login, R.string.login_bottom_navbar),
}