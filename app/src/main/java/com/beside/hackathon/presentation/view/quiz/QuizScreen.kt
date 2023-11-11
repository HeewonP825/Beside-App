package com.beside.hackathon.presentation.view.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beside.hackathon.core.utils.Colors.BUTTON_YELLOW
import com.beside.hackathon.core.utils.Colors.WHITE
import com.beside.hackathon.core.utils.TextSyles.BUTTON_TEXT_STYLE
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun QuizScreen() {
    DefaultLayout(
        title = "퀴즈 풀기",
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("한 주 동안 공부한 내용을 테스트해보아요:)")
            TextButton(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp).height(76.dp)
                    .background(
                        color = BUTTON_YELLOW,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Text("퀴즈 풀기 시작", style = BUTTON_TEXT_STYLE.copy(
                    color = WHITE
                ))
            }
        }
    }
}