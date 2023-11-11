package com.beside.hackathon.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beside.hackathon.core.utils.Colors
import com.beside.hackathon.core.utils.Constant
import com.beside.hackathon.core.utils.Constant.BORDER_RADIUS
import com.beside.hackathon.core.utils.TextSyles

@Composable
fun CustomButton(onClick : () -> Unit, content: @Composable RowScope.() -> Unit){
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .height(76.dp)
            .background(
                color = Colors.BUTTON_YELLOW,
                shape = RoundedCornerShape(BORDER_RADIUS)
            )
    ) {
        content()
    }
}