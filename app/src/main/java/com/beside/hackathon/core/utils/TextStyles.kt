package com.beside.hackathon.core.utils

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.Colors.WHITE

object TextStyles {
    val BIG_TITLE_STYLE = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.W700
    )
    val MEDIUMN_TITLE_STYLE = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W700
    )

    val TITLE_TEXT_STYLE = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W700
    )

    val BUTTON_TEXT_STYLE = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W700,
        color = WHITE
    )

    val TITLE_TEXT2_STYLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W700
    )
    val TITLE_TEXT3_STYLE = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W700
    )
    val TITLE_TEXT4_STYLE = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W700
    )


    val CONTENT_TEXT1_STYLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500
    )

    val CONTENT_TEXT2_STYLE = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500
    )
    val CONTENT_TEXT3_STYLE = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    )

    val CONTENT_SMALL1_STYLE = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500
    )

    val CONTENT_SMALL2_STYLE = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W500
    )

    val PYEONG_FONT = FontFamily(
        Font(R.font.pyeongchangpeace_light, FontWeight.W300),
        Font(R.font.pyeongchangpeace_bold, FontWeight.W700)
    )
    val PYEONG_TITLE_STLYE = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.W300,
        fontFamily = PYEONG_FONT,
    )

    val PYEONG_CONTENT1_STLYE = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W300,
        fontFamily = PYEONG_FONT,
    )
    val PYEONG_CONTENT2_STLYE = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W300,
        fontFamily = PYEONG_FONT,
    )
}