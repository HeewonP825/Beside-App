package com.beside.hackathon.data.repository.quiz

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.quiz.Option
import com.beside.hackathon.data.model.quiz.Question
import com.beside.hackathon.data.model.quiz.Quiz
import com.beside.hackathon.data.model.quiz.QuizSubmitRequest
import com.beside.hackathon.data.model.quiz.QuizSubmitResponse
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val apiService: ApiService
){


    suspend fun getTodayQuiz(): Quiz {
        return Quiz(
            1,"테스트 퀴즈명", listOf(
                Question(
                    1,"테스트 질문1", listOf(
                        Option(1,"테스트 옵션1"),
                        Option(2,"테스트 옵션2"),
                        Option(3,"테스트 옵션3"),
                        Option(4,"테스트 옵션4"),
                    )
                ),
                Question(
                    2,"테스트 질문2", listOf(
                        Option(5,"테스트 옵션5"),
                        Option(6,"테스트 옵션6"),
                        Option(7,"테스트 옵션7"),
                        Option(8,"테스트 옵션8"),
                    )
                ),
            )
        )

        //return apiService.getTodayQuiz()
    }
    suspend fun submitQuiz(
        selectedOptionIds: List<Long>,
    ): QuizSubmitResponse {
        val body = QuizSubmitRequest(
            selectedOptionIds = selectedOptionIds,
        )
        return apiService.submitQuiz(body)
    }
}