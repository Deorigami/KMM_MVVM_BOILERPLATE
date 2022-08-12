/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.eyedea

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "books") {
        composable("books") {
            BookListScreen(
                onOpenBook = {
                    navController.navigate("books/$it/review")
                }
            )
        }
    }
}
