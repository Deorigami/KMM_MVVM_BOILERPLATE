package com.eyedea.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.eyedea.auth.screen.AuthNavGraph
import com.eyedea.dashboard.screen.DashboardNavGraph
import com.eyedea.feature_product.ProductNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

@ExperimentalCoilApi
@ExperimentalComposeUiApi
object RootNavGraph: NavGraphSpec {

    override val route = "root"
    override val startRoute: Route = DashboardNavGraph

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        DashboardNavGraph,
        ProductNavGraph,
        AuthNavGraph
    )
}