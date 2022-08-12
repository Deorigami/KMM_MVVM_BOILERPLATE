/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.eyedea

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.shared.viewmodels.SectionPageViewModel
import dev.shared.base.BaseState
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.RawStringDesc
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

@Composable
fun BookListScreen(
    viewModel: SectionPageViewModel = viewModel(
        factory = createViewModelFactory { SectionPageViewModel() }
    ),
    onOpenBook: (Int) -> Unit = {}
) {
    val state by viewModel.sectionPageData.state.collectAsState()

    when (@Suppress("NAME_SHADOWING") val state = state) {
        is BaseState.Error -> ErrorState(message = RawStringDesc(state.data.message ?: ""))
        is BaseState.Loading -> LoadingState()
        is BaseState.Success -> SuccessState(state.data.titleA)
    }
}

@Composable
private fun EmptyState(message: StringDesc) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message.localized())
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyStatePreview() {
    EmptyState(message = "no items".desc())
}

@Composable
private fun ErrorState(message: RawStringDesc) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message.localized(), color = Color.Red)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorStatePreview() {
    ErrorState(message = "No internet :(".desc())
}

@Composable
private fun LoadingState() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoadingStatePreview() {
    LoadingState()
}

@Composable
private fun SuccessState(string: String? = null) {
    Text(text = string ?: "")
}
