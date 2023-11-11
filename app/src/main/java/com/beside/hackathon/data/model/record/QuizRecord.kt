package com.beside.hackathon.data.model.record

import java.util.Date

data class QuizRecord(
    val quizId: Int,
    val startData: Date,
    val endData: Date,
    val subject:String,
    val correctQuizCount: Int,
    val wrongQuizCount: Int,
    val answerRate: Double,
)
