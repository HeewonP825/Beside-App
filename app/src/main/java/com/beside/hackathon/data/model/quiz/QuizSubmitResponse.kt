package com.beside.hackathon.data.model.quiz

data class QuizSubmitResponse(
    val nickname: String,
    val correctAnswerCount: Int,
    val totalQuestionCount: Int,
)
