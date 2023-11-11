package com.beside.hackathon.presentation.view.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun QuizScreen() {
    DefaultLayout(title = "퀴즈 풀기") {
        Column {
            Text("한 주 동안 공부한 내용을 테스트해보아요:)")
            TextButton(onClick = {

            }) {
                Text("퀴즈 풀기")
            }
        }
    }
}