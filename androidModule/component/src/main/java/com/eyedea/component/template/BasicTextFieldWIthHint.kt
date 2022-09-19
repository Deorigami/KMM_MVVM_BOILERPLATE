package com.eyedea.component.template

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import com.eyedea.component.themes.MediumTextStyle

@Composable
fun BasicTextFieldWithHint(
    modifier: Modifier = Modifier,
    onTextInputChange : (String) -> Unit = {},
    hint : String = "",
    value: String = "",
    hintStyle : TextStyle = MediumTextStyle.Medium_14,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    val text = remember { mutableStateOf("") }
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            text.value = it
            onTextInputChange.invoke(it)
        },
        textStyle = MediumTextStyle.Medium_16,
        decorationBox = { innerTextField ->
            if (text.value.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = hint,
                        style = hintStyle
                    )
                }
            }
            innerTextField()
        }
    )
}