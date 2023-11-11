package com.beside.hackathon.data.model.quizhistory


data class QuizHistory(
    val quizId: Int,
    val startDate: String,
    val endDate: String,
    val subject:String,
    val correctQuizCount: Int,
    val wrongQuizCount: Int,
    val answerRate: Double,
)
