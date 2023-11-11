package com.beside.hackathon.core.utils

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beside.hackathon.core.utils.Colors.WHITE

object TextStyles {
    val BIG_TITLE_STYLE = TextStyle(
        fontSize = 40.sp,
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

    val CONTENT_TEXT1_STYLE = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500
    )

    val CONTENT_TEXT2_STYLE = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500
    )
}