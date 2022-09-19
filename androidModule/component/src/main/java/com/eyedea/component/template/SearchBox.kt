package com.eyedea.component.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.eyedea.component.R
import com.eyedea.component.themes.HalfGrey
import com.eyedea.component.themes.MediumTextStyle
import com.eyedea.component.themes.OffGrey

@Composable
@Preview
fun SearchBox(
    modifier: Modifier = Modifier,
    text : String = "",
    onTextChange : (String) -> Unit = {},
) {
    Card(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth(),
        backgroundColor = Color.OffGrey,
        shape = RoundedCornerShape(10.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            val (textField, icon) = createRefs()
            BasicTextFieldWithHint(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(textField) {
                        linkTo(parent.start, icon.start, 0.dp, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                value = text,
                onTextInputChange = { onTextChange(it) },
                hint = "Cari Nama Produk",
                hintStyle = MediumTextStyle.Medium_16.copy(
                    color = Color.HalfGrey
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(icon){
                        linkTo(parent.top, parent.bottom)
                        linkTo(textField.start, parent.end)
                    }
            )
            createHorizontalChain(textField, icon, chainStyle = ChainStyle.Spread)
        }
    }
}