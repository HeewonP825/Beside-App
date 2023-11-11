package com.beside.hackathon.presentation.view.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.beside.hackathon.R
import com.beside.hackathon.core.utils.TextStyles.BUTTON_TEXT_STYLE
import com.beside.hackathon.presentation.component.CustomButton
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun QuizScreen(navController: NavController) {

    DefaultLayout(
        title = "퀴즈 풀기",
        backButtonOnClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("한 주 동안 공부한 내용을 테스트해보아요:)")
            Box(modifier = Modifier.padding(20.dp)) {
                CustomButton(onClick = {
                    navController.navigate(R.id.action_quiz_fragment_to_quizSolveFragment)
                }) {
                    Text("퀴즈 풀기 시작", style = BUTTON_TEXT_STYLE)
                }
            }
        }
    }
}

