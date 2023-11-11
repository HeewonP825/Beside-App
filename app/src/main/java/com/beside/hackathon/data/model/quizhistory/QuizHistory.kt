package com.beside.hackathon.data.model.quizhistory

import java.util.Date

data class QuizHistory(
    val quizId: Int,
    val startData: Date,
    val endData: Date,
    val subject:String,
    val correctQuizCount: Int,
    val wrongQuizCount: Int,
    val answerRate: Double,
)
