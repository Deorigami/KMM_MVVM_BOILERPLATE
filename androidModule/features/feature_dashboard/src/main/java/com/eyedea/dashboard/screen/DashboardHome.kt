package com.eyedea.dashboard.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.eyedea.component.cards.group.ProductCategoryCardGroup
import com.eyedea.component.cards.group.ProductItemCardGroup
import com.eyedea.component.cards.group.ProductItemCardGroupData
import com.eyedea.component.dialogs.LoginDialog
import com.eyedea.component.template.Header
import com.eyedea.component.template.SearchBox
import com.eyedea.component.themes.SoftGrey
import com.eyedea.core.utils.deserialize
import com.eyedea.dashboard.DashboardScreenNavigator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.shared.base.BaseRespondDto
import dev.shared.base.BaseState
import dev.shared.data.web.dto.ProductItemDto
import dev.shared.data.web.mapper.ProductItemsDtoMapper
import dev.shared.domain.entity.ProductCategoryItemEntity
import dev.shared.domain.entity.ProductItemEntity
import dev.shared.viewmodels.SectionPageViewModel

@Composable
@Destination
fun DashboardHome(
    viewModel : SectionPageViewModel = viewModel(factory = createViewModelFactory { SectionPageViewModel() }),
    navigator: DashboardScreenNavigator
){
    val sectionPageState by viewModel.productCategory.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when(val state: BaseState<List<ProductCategoryItemEntity>> = sectionPageState){
            is BaseState.Success -> SuccessState(categoryList = state.data, navigator)
            else -> {}
        }
    }
}

@Composable
private fun SuccessState(
    categoryList : List<ProductCategoryItemEntity> = emptyList(),
    navigator: DashboardScreenNavigator
) {
    val scrollState = rememberScrollState()
    val isDialogLoginShown = remember { mutableStateOf(false) }
    var searchInputText by remember{ mutableStateOf("") }
    if (isDialogLoginShown.value) LoginDialog(openCustomDialog = isDialogLoginShown){navigator.navigateToAuthPage()}
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            title = "Mega Mall",
            modifier = Modifier
                .shadow(5.dp)
                .background(Color.White)
                .padding(horizontal = 24.dp)
        )
        Column(
            modifier = Modifier
                .background(color = Color.SoftGrey)
                .verticalScroll(scrollState)
        ) {
            SearchBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                text = searchInputText,
                onTextChange = { searchInputText = it }
            )
            ProductCategoryCardGroup(categoryList = categoryList){
                isDialogLoginShown.value = !isDialogLoginShown.value
            }
            ProductItemCardGroup(
                title = "Featured Product",
                items = getProductItemList().map { ProductItemCardGroupData(
                    title = it.title,
                    imagePainter = rememberAsyncImagePainter(model = it.image),
                    price = it.price,
                    rating = it.rating,
                    reviewsCount = it.reviewsCount
                ) },
                padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                onItemPressed = {

                }
            )
        }
    }
}

fun getProductItemList(): List<ProductItemEntity> {
    val productList = """
        {
            "status" : 1,
            "message" : "berhasil",
            "data" : [
                {
                    "image" : "https://lh3.googleusercontent.com/r_8FeiUhy4p2aG9m8eQYeWCBMfIAREHy4Kv3ZieCCVmQoTRQg7ECmtW7iabWBEkaugRvMHw6LGIz-8JLIZ50tDVX8G6Q1MR2UBKcC4he2UkICXjEIGl_wl-otnIwdiF4TNygTYECnQ=w2400",
                    "title" : "TMA-2 HD Wireless",
                    "price" : "Rp. 1.500.000",
                    "rating" : "4.6",
                    "reviews_count" : "86 Reviews"
                },
                {
                    "image" : "https://lh3.googleusercontent.com/r_8FeiUhy4p2aG9m8eQYeWCBMfIAREHy4Kv3ZieCCVmQoTRQg7ECmtW7iabWBEkaugRvMHw6LGIz-8JLIZ50tDVX8G6Q1MR2UBKcC4he2UkICXjEIGl_wl-otnIwdiF4TNygTYECnQ=w2400",
                    "title" : "TMA-2 HD Wireless",
                    "price" : "Rp. 1.500.000",
                    "rating" : "4.6",
                    "reviews_count" : "86 Reviews"
                },
                {
                    "image" : "https://lh3.googleusercontent.com/r_8FeiUhy4p2aG9m8eQYeWCBMfIAREHy4Kv3ZieCCVmQoTRQg7ECmtW7iabWBEkaugRvMHw6LGIz-8JLIZ50tDVX8G6Q1MR2UBKcC4he2UkICXjEIGl_wl-otnIwdiF4TNygTYECnQ=w2400",
                    "title" : "TMA-2 HD Wireless",
                    "price" : "Rp. 1.500.000",
                    "rating" : "4.6",
                    "reviews_count" : "86 Reviews"
                },
                {
                    "image" : "https://lh3.googleusercontent.com/r_8FeiUhy4p2aG9m8eQYeWCBMfIAREHy4Kv3ZieCCVmQoTRQg7ECmtW7iabWBEkaugRvMHw6LGIz-8JLIZ50tDVX8G6Q1MR2UBKcC4he2UkICXjEIGl_wl-otnIwdiF4TNygTYECnQ=w2400",
                    "title" : "TMA-2 HD Wireless",
                    "price" : "Rp. 1.500.000",
                    "rating" : "4.6",
                    "reviews_count" : "86 Reviews"
                },
                {
                    "image" : "https://lh3.googleusercontent.com/r_8FeiUhy4p2aG9m8eQYeWCBMfIAREHy4Kv3ZieCCVmQoTRQg7ECmtW7iabWBEkaugRvMHw6LGIz-8JLIZ50tDVX8G6Q1MR2UBKcC4he2UkICXjEIGl_wl-otnIwdiF4TNygTYECnQ=w2400",
                    "title" : "TMA-2 HD Wireless",
                    "price" : "Rp. 1.500.000",
                    "rating" : "4.6",
                    "reviews_count" : "86 Reviews"
                }
            ]
        }
    """.trimIndent()
    val baseRespondDto = deserialize<BaseRespondDto<List<ProductItemDto>>>(productList)
    return ProductItemsDtoMapper().invoke(baseRespondDto).data
}

