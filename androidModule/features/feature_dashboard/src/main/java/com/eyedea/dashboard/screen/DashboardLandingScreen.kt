package com.eyedea.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eyedea.component.themes.MediumTextStyle.Medium_10
import com.eyedea.component.utils.fromHex
import com.eyedea.dashboard.DashboardScreenNavigator
import com.eyedea.dashboard.composables.BottomBarItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination
fun DashboardLandingScreen(
    navigator: DashboardScreenNavigator
) {
    val bottomBarItem = BottomBarItem.values().toList()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = bottomBarItem.size,
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .weight(1f, fill = true)
        ) { idx ->
            when(idx){
                0 -> DashboardHome(navigator = navigator)
                1 -> DashboardWhistlist()
                2 -> DashboardOrder()
                3 -> DashboardLogin()
            }
        }
        BottomBar(bottomBarItem){
            scope.launch {
                pagerState.animateScrollToPage(it)
            }
        }
    }
}

@Composable
fun BottomBar(
    items: List<BottomBarItem>,
    onPressed: (Int) -> Unit = {}
){
    val widthFraction = 1f / items.size
    var activeBottomBarItem by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemsIndexed(items) { index, item ->
                BottomBarItem(
                    item = item,
                    activePosition = activeBottomBarItem,
                    position = index,
                    modifier = Modifier
                        .fillParentMaxWidth(widthFraction)
                ){
                    onPressed(it)
                    activeBottomBarItem = it
                }
            }
        }
    }
}

@Composable
fun BottomBarItem(
    item : BottomBarItem,
    activePosition : Int,
    position : Int,
    modifier: Modifier = Modifier,
    onPressed : (Int) -> Unit = {}
){
    val iconTint = if (activePosition == position) fromHex("#3669C9") else fromHex("#0C1A30")

    Column(
        modifier = modifier
            .wrapContentHeight()
            .clickable { onPressed(position) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(iconTint),
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = item.label),
            modifier = Modifier
                .padding(vertical = 8.dp),
            color = iconTint,
            style = Medium_10
        )
    }
}