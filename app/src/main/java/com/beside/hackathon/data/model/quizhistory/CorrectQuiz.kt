package com.beside.hackathon.data.model.quizhistory

import com.beside.hackathon.data.model.quiz.Option

data class CorrectQuiz(
    val quizId:Long,
    val quizName:String,
    val questions:List<CorrectQuestion>
)

data class CorrectQuestion(
    val questionId:Long,
    val questionName:String,
    val imageUrl: String,
    val selectedOptionId:Int,
    val answerOptionId:Int,
    val explanation:String,
    val options:List<Option>
)