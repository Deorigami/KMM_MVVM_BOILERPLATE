package com.eyedea.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.eyedea.auth.R
import com.eyedea.component.cards.AuthNumberInput
import com.eyedea.component.cards.CustomButton
import com.eyedea.component.themes.MediumTextStyle
import com.eyedea.component.utils.fromHex
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Composable
@Destination
@RootNavGraph(start = true)
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Vertical)
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_auth_banner),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .scale(scale = 1.2f)
                    .weight(1f, fill = true)
                    .background(Color.Red)
                    .fillMaxSize()
            )
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(horizontal = 24.dp)
        ) {
            val (otp, google, facebook) = createRefs()
            AuthNumberInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .constrainAs(otp) {
                        top.linkTo(parent.top)
                    }
            )
            CustomButton(
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 4.dp)
                    .constrainAs(google) {
                        linkTo(otp.bottom,parent.bottom, 16.dp, 16.dp)
                        linkTo(parent.start, facebook.start)
                        width = Dimension.fillToConstraints
                    },
                image = painterResource(id = R.drawable.ic_google),
                text = "Google"
            )

            CustomButton(
                image = painterResource(id = R.drawable.ic_facebook),
                text = "Facebook",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(50.dp)
                    .constrainAs(facebook) {
                        linkTo(otp.bottom,parent.bottom, 16.dp, 16.dp)
                        linkTo(google.end, parent.end)
                        width = Dimension.fillToConstraints

                    },
                cardBackgroundColor = fromHex("#3B5998"),
                textColor = Color.White
            )
            createHorizontalChain(google, facebook, chainStyle = ChainStyle.Spread)
        }
    }

}