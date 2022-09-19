package com.eyedea.component.cards

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.text.isDigitsOnly
import com.eyedea.component.R
import com.eyedea.component.themes.*

@Composable
fun AuthNumberInput(
    modifier: Modifier = Modifier,
    onTextInputChange : (String) -> Unit = {},
    onOTPButtonPressed : () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = Color.SoftGrey,
        shape = RoundedCornerShape(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
        ) {
            val (leftIcon, textInput, otpButton) = createRefs()
            var textFieldValue  by remember { mutableStateOf(TextFieldValue("")) }
            Image(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
                    .constrainAs(leftIcon) {
                        start.linkTo(parent.start, 16.dp)
                        linkTo(parent.top, parent.bottom, 12.dp, 12.dp)
                    }
            )
            BasicTextField(
                value = textFieldValue,
                onValueChange = {
                    val numberOnlyText = it.text.toList().filter { it.isDigit() }.joinToString("")
                    textFieldValue = it.copy(
                        text = numberOnlyText
                    )
                    onTextInputChange.invoke(numberOnlyText)
                },
                singleLine = true,
                decorationBox = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (textFieldValue.text.isEmpty()) Text(
                            text = "Phone Number",
                            style = MediumTextStyle.Medium_14,
                            color = Color.Gray
                        )
                        it()
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = MediumTextStyle.Medium_18,
                modifier = Modifier
                    .constrainAs(textInput){
                        linkTo(leftIcon.end, otpButton.start, 16.dp, 16.dp)
                        linkTo(parent.top, parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            )
            Card(
                backgroundColor = Color.EarthGreen,
                modifier = Modifier
                    .clickable { onOTPButtonPressed() }
                    .constrainAs(otpButton){
                        end.linkTo(parent.end, 8.dp)
                        linkTo(parent.top, parent.bottom, 8.dp,8.dp)
                        height = Dimension.fillToConstraints
                    },
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Send OTP",
                        style = MediumTextStyle.Medium_16,
                        color = Color.White,
                        modifier = Modifier

                    )
                }
            }
        }
    }
}