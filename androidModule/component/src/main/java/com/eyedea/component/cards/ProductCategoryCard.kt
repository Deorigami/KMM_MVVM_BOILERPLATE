

package com.eyedea.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.eyedea.component.utils.fromHex
import dev.shared.domain.entity.ProductCategoryItemEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ProductCategoryCard(
    productCategory: ProductCategoryItemEntity = ProductCategoryItemEntity.DEFAULT,
    onCardPressed: () -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            onClick = { onCardPressed() },
            modifier = Modifier
                .size(48.dp),
            backgroundColor = fromHex(productCategory.backgroundColor),
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp
        ) {
            Image(
                painter = rememberAsyncImagePainter(productCategory.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = productCategory.category,
            fontSize = 14.sp
        )
    }
}