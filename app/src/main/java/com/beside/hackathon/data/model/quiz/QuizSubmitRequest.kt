package com.beside.hackathon.data.model.quiz

data class QuizSubmitRequest(
    val quizId: Int,
    val answers: List<Answer>,
)

data class Answer(
    val questionId: Long,
    val optionId: Int,
)
