

package com.eyedea.component.themes

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eyedea.component.R

val Poppins = FontFamily(
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.poppins_regular, weight = FontWeight.Normal),
    Font(R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
)


    @OptIn(ExperimentalTextApi::class)
object MediumTextStyle {
    val Medium_18 = TextStyle(
        fontSize = 18.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )

    val Medium_16 = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )

    @OptIn(ExperimentalTextApi::class)
    val Medium_14 = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Medium
    )

    @OptIn(ExperimentalTextApi::class)
    val Medium_12 = TextStyle(
        fontSize = 12.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Medium
    )

    @OptIn(ExperimentalTextApi::class)
    val Medium_10 = TextStyle(
        fontSize = 10.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Medium
    )
}

object BoldTextStyle {
    @OptIn(ExperimentalTextApi::class)
    val Bold_18 = TextStyle(
        fontSize = 18.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Bold
    )
    @OptIn(ExperimentalTextApi::class)
    val Bold_16 = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Bold
    )
    @OptIn(ExperimentalTextApi::class)
    val Bold_12 = TextStyle(
        fontSize = 12.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Bold
    )
}

object RegularTextStyle {
    @OptIn(ExperimentalTextApi::class)
    val Regular_10 = TextStyle(
        fontSize = 10.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    )
    @OptIn(ExperimentalTextApi::class)
    val Regular_14 = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    )
}
