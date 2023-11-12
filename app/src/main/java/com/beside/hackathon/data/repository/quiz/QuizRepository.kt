package com.beside.hackathon.data.repository.quiz

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.quiz.Answer
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
        quizId: Int,
        answers: List<Answer>,
    ): QuizSubmitResponse {
        val body = QuizSubmitRequest(
            quizId = quizId,
            answers = answers
        )
        return apiService.submitQuiz(body)
    }
}