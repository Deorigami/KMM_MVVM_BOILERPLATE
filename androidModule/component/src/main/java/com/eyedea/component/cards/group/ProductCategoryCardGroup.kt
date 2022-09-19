

package com.eyedea.component.cards.group

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eyedea.component.cards.ProductCategoryCard
import com.eyedea.component.template.SegmentHeader
import dev.shared.domain.entity.ProductCategoryItemEntity


@Composable
fun ProductCategoryCardGroup(
    categoryList : List<ProductCategoryItemEntity>,
    modifier: Modifier = Modifier,
    onPressed : (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SegmentHeader(
            segmentTitle = "Categories",
            modifier = Modifier
                .padding(horizontal = 24.dp),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(items = categoryList){ index, item ->
                ProductCategoryCard(
                    productCategory = item
                ){
                    onPressed(index)
                }
            }
        }
    }
}