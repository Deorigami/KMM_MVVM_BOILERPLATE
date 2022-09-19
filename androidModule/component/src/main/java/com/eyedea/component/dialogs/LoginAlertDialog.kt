package com.eyedea.component.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.eyedea.component.R
import com.eyedea.component.themes.*
import com.eyedea.component.utils.noRippleClickable

@Composable
fun LoginDialog(openCustomDialog : MutableState<Boolean>, onPrimaryButtonPressed: () -> Unit = {}){
    Dialog(onDismissRequest = {  }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            ConstraintLayout {
                val (label, closeButton, firstLiner, handIcon, title, subtitle, loginButton) = createRefs()
                Text(
                    text = "Login Account",
                    modifier = Modifier
                        .constrainAs(label){
                            start.linkTo(parent.start, 25.dp)
                            top.linkTo(parent.top, 25.dp)
                        },
                    style = BoldTextStyle.Bold_18
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "",
                    modifier = Modifier
                        .noRippleClickable { openCustomDialog.value = false }
                        .constrainAs(closeButton){
                            top.linkTo(parent.top, 25.dp)
                            end.linkTo(parent.end, 25.dp)
                        }
                )
                Column(
                    modifier = Modifier
                        .background(Color.SoftGrey)
                        .fillMaxWidth()
                        .height(1.dp)
                        .constrainAs(firstLiner) {
                            linkTo(start = parent.start, end = parent.end, 25.dp, 25.dp)
                            top.linkTo(closeButton.bottom, 25.dp)
                        }
                ){}
                Image(
                    painter = painterResource(id = R.drawable.ic_hand),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(handIcon) {
                            top.linkTo(firstLiner.bottom, 30.dp)
                            linkTo(parent.start, parent.end, bias = 0.5f)
                        }
                )
                Text(
                    text = "Anda perlu masuk terlebih dahulu",
                    style = MediumTextStyle.Medium_16,
                    modifier = Modifier
                        .constrainAs(title){
                            top.linkTo(handIcon.bottom, 16.dp)
                            linkTo(parent.start, parent.end, 25.dp, 25.dp, bias = 0.5f)
                            width = Dimension.fillToConstraints
                        },
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Silahkan login/ register telebih dahulu untuk melakukan transaksi",
                    style = RegularTextStyle.Regular_14,
                    modifier = Modifier
                        .constrainAs(subtitle){
                            top.linkTo(title.bottom, 8.dp)
                            linkTo(parent.start, parent.end, 25.dp, 25.dp, bias = 0.5f)
                            width = Dimension.fillToConstraints
                        },
                    color = Color.HalfGrey,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {
                        openCustomDialog.value = false
                        onPrimaryButtonPressed()
                    },
                    modifier = Modifier
                        .constrainAs(loginButton) {
                            top.linkTo(subtitle.bottom, 30.dp)
                            bottom.linkTo(parent.bottom, 25.dp)
                            linkTo(parent.start, parent.end, 25.dp, 25.dp)
                            width = Dimension.fillToConstraints
                        }
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.BlueOcean
                    )
                ) {
                    Text(
                        text = "Login",
                        style = MediumTextStyle.Medium_14,
                        color = Color.White
                    )
                }
            }
        }
    }
}
