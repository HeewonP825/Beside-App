package com.beside.hackathon.data.model.record

data class CardNewsRecord(
    val newsId:Long,
    val title: String,
    val takenTime: Int,
    val publishedDate: String,
    val urls : List<String>
)