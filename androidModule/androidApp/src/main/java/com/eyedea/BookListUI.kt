package com.eyedea

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.eyedea.component.utils.fromHex
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.RawStringDesc
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.shared.base.BaseState
import dev.shared.domain.entity.ProductCategoryItemEntity
import dev.shared.viewmodels.SectionPageViewModel

@Composable
fun BookListScreen(
    viewModel: SectionPageViewModel = viewModel(
        factory = createViewModelFactory { SectionPageViewModel() }
    ),
    navigator: DestinationsNavigator,
    onOpenBook: (Int) -> Unit = {}
) {
    val productCategory by viewModel.productCategory.state.collectAsState()

    when (@Suppress("NAME_SHADOWING") val state = productCategory) {
        is BaseState.Error -> ErrorState(message = RawStringDesc(state.data.message ?: ""))
        is BaseState.Loading -> LoadingState()
        is BaseState.Success -> SuccessState(state.data, navigator)
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
private fun SuccessState(
    categoryList : List<ProductCategoryItemEntity> = emptyList(),
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = categoryList,
                itemContent = {

                }
            )
        }
    }
}
