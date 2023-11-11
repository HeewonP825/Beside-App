package com.beside.hackathon.presentation.view.quizhistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.beside.hackathon.core.utils.Constant.DEFAULT_PADDING_H
import com.beside.hackathon.presentation.view.common.DefaultLayout

@Composable
fun QuizCorrectScreen(navController: NavController, quizHistoryViewModel: QuizHistoryViewModel, id:Long) {
    DefaultLayout(
        title = "퀴즈 결과",
        backButtonOnClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = DEFAULT_PADDING_H),
        ) {

        }
    }
}