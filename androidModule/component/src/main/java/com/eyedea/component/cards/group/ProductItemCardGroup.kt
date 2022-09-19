

package com.eyedea.component.cards.group

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.eyedea.component.cards.ProductItemCard
import com.eyedea.component.template.SegmentHeader
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject

@Composable
fun ProductItemCardGroup(
    modifier: Modifier = Modifier,
    title: String,
    rightText: String = "See All",
    items : List<ProductItemCardGroupData> = emptyList(),
    onItemPressed : (Int) -> Unit = {},
    padding: PaddingValues = PaddingValues(0.dp)
){
    Column(
        modifier = modifier
            .padding(top = padding.calculateTopPadding())
            .fillMaxWidth()
    ) {
        SegmentHeader(
            modifier = Modifier.padding(horizontal = padding.calculateStartPadding(LayoutDirection.Ltr)),
            segmentTitle = title,
            rightTitle = rightText
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = padding.calculateStartPadding(LayoutDirection.Ltr))
        ){
            itemsIndexed(items = items){ index, item ->
                ProductItemCard(
                    modifier = Modifier,
                    item.title,
                    item.imagePainter,
                    item.price,
                    item.rating,
                    item.reviewsCount,
                ) { onItemPressed(index) }
            }
        }
    }
}

data class ProductItemCardGroupData(
    val title: String,
    val imagePainter: Painter,
    val price: String,
    val rating: String,
    val reviewsCount: String,
)
