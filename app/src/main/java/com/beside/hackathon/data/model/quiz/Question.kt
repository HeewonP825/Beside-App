package com.beside.hackathon.data.model.quiz

data class Question(
    val questionId: Long,
    val questionName: String,
    val options: List<Option>,
)

