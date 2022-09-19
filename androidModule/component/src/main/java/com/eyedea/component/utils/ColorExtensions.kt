

package com.eyedea.component.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun fromHex(color: String) : Color = Color(android.graphics.Color.parseColor(color))

inline fun Modifier.noRippleClickable(crossinline onClick : () -> Unit) : Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember(::MutableInteractionSource)
    ) {
        onClick()
    }
}