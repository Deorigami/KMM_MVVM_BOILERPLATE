

package com.eyedea.component.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eyedea.component.themes.BlueOcean
import com.eyedea.component.themes.MediumTextStyle.Medium_12
import com.eyedea.component.themes.MediumTextStyle.Medium_16
import com.eyedea.component.utils.noRippleClickable

@Composable
fun SegmentHeader(
    modifier: Modifier = Modifier,
    segmentTitle : String,
    rightTitle: String = "See All",
    onRightTitlePressed : () -> Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = segmentTitle, style = Medium_16)
        Text(
            text = rightTitle,
            color = Color.BlueOcean,
            style = Medium_12,
            modifier = Modifier
                .noRippleClickable { onRightTitlePressed() }
        )
    }
}