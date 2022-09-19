

package com.eyedea.component.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eyedea.component.R
import com.eyedea.component.themes.BlueOcean
import com.eyedea.component.themes.BoldTextStyle.Bold_18
import com.eyedea.component.themes.RedVelvet
import com.eyedea.component.utils.noRippleClickable

@Composable
@Preview
fun Header(
    title : String = "",
    modifier: Modifier = Modifier,
    hasBackIcon : Boolean = true,
    onBackIconPressed : () -> Unit = {},
    cartRedDotIsVisible : Boolean = false,
    notificationRedDotIsVisible : Boolean = false,
    onCartPressed : () -> Unit = {},
    onNotificationPressed : () -> Unit = {},
){
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        val (bellIcon, cartIcon, headerTitle) = this.createRefs()
        
        Box(
            modifier = Modifier
                .noRippleClickable {
                    onCartPressed()
                }
                .constrainAs(cartIcon) {
                    linkTo(top = parent.top, bottom = parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "",
                modifier = Modifier
            )
            if (cartRedDotIsVisible) {
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(10.dp),
                    backgroundColor = Color.RedVelvet,
                    shape = RoundedCornerShape(50)
                ){}
            }
        }

        Box(
            modifier = Modifier
                .noRippleClickable { onNotificationPressed() }
                .constrainAs(bellIcon) {
                    end.linkTo(cartIcon.start, 16.dp)
                    linkTo(parent.top, parent.bottom)
                },
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "",
                modifier = Modifier
            )
            if (notificationRedDotIsVisible) {
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(10.dp),
                    backgroundColor = Color.RedVelvet,
                    shape = RoundedCornerShape(50)
                ){}
            }
        }


        Text(
            text = title,
            color = Color.BlueOcean,
            style = Bold_18,
            modifier = Modifier
                .constrainAs(headerTitle){
                    linkTo(parent.top , parent.bottom)
                    linkTo(parent.start, parent.end)
                }
        )
    }
}