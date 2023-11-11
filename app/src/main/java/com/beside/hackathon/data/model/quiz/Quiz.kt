package com.beside.hackathon.data.model.quiz


data class Quiz(
    val quizId: Int,
    val quizName: String,
    val questions: List<Question>
)