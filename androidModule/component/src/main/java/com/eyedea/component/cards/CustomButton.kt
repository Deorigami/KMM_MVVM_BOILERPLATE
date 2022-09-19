package com.eyedea.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.eyedea.component.themes.MediumTextStyle

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    image: Painter,
    text: String,
    textColor: Color = Color.Black,
    cardBackgroundColor: Color = Color.White
) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = cardBackgroundColor,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
                    .padding(10.dp),
            )
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = text,
                color = textColor,
                style = MediumTextStyle.Medium_16
            )
        }
    }
}