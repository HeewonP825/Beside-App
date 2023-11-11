package com.beside.hackathon.data.model.quiz

data class QuizSubmitResponse(
    val userName: String,
    val correctAnswerCount: Int,
    val totalQuestionCount: Int,
)
