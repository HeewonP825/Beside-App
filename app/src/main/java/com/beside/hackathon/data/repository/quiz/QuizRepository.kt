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

        return apiService.getTodayQuiz()
    }
    suspend fun submitQuiz(
        selectedOptionIds: List<Int>,
    ): QuizSubmitResponse {
        return QuizSubmitResponse(
            "user",10,10
        )

        val body = QuizSubmitRequest(
            selectedOptionIds = selectedOptionIds,
        )
        return apiService.submitQuiz(body)
    }
}