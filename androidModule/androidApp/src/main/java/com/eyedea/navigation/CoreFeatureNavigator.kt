package com.eyedea.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.eyedea.auth.screen.destinations.LoginScreenDestination
import com.eyedea.dashboard.DashboardScreenNavigator
import com.eyedea.feature_product.destinations.ProductDetailLandingDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationSpec

@ExperimentalCoilApi
@ExperimentalComposeUiApi
class CoreFeatureNavigator(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : DashboardScreenNavigator {
    override fun navigateToProductLanding() {
        navController.navigate(ProductDetailLandingDestination)
    }

    override fun navigateToAuthPage() {
        navController.navigate(LoginScreenDestination)
    }
}