package com.beside.hackathon.presentation.view.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.beside.hackathon.presentation.view.common.DefaultLayout
import com.beside.hackathon.presentation.viewmodel.quiz.QuizVIewModel


@Composable
fun QuizSolveScreen(viewModel: QuizVIewModel){
    val quizState = viewModel.quiz.collectAsState()
    var page by remember { mutableStateOf(quizState.value.questions.size) }

    DefaultLayout(title = "퀴즈 풀기"){
        Column{
            Column {
                Text("Q${page}. ${quizState.value.questions[page-1].questionName}")

                quizState.value.questions[page-1].options.forEach {
                    TextButton(onClick = {

                    }) {
                        Text(it.content)
                    }
                }
            }
            TextButton(onClick = {
                page += 1
            }){
                Text("퀴즈 풀기")
            }
        }
    }
}