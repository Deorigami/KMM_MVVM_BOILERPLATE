

package com.eyedea.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.eyedea.component.R
import com.eyedea.component.themes.BoldTextStyle
import com.eyedea.component.themes.MediumTextStyle
import com.eyedea.component.themes.RedVelvet
import com.eyedea.component.themes.RegularTextStyle
import com.eyedea.component.utils.noRippleClickable

@Composable
fun ProductItemCard(
    modifier: Modifier = Modifier,
    title: String,
    imagePainter: Painter,
    price: String,
    rating: String,
    reviewsCount: String,
    onDotMenuPressed : () -> Unit = {}
) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentSize()
        ) {
            val (image, productTitle, productPrice, reviewComponentsContainer) = createRefs()
            Image(
                modifier = Modifier
                    .size(125.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, 16.dp)
                        linkTo(parent.start, parent.end, 16.dp, 16.dp)
                    },
                painter = imagePainter,
                contentDescription = ""
            )
            Text(
                text = title,
                style = MediumTextStyle.Medium_14,
                modifier = Modifier
                    .constrainAs(productTitle) {
                        top.linkTo(image.bottom, 16.dp)
                        linkTo(parent.start, parent.end, 16.dp, 16.dp)
                    }
            )
            Text(
                text = price,
                style = BoldTextStyle.Bold_12,
                color = Color.RedVelvet,
                modifier = Modifier
                    .constrainAs(productPrice) {
                        top.linkTo(productTitle.bottom)
                        linkTo(parent.start, parent.end, 16.dp, 16.dp, bias = 0f)
                    }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .constrainAs(reviewComponentsContainer) {
                        top.linkTo(productPrice.bottom, 16.dp)
                        linkTo(parent.start, parent.end)
                        width = Dimension.fillToConstraints
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = ""
                )
                Text(
                    text = rating,
                    modifier = Modifier
                        .padding(start = 4.dp),
                    style = RegularTextStyle.Regular_10
                )
                Text(
                    text = reviewsCount,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    style = RegularTextStyle.Regular_10,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_vertical_dot_menu),
                    contentDescription = "",
                    modifier = Modifier
                        .noRippleClickable { onDotMenuPressed() }
                )
            }
        }
    }
}