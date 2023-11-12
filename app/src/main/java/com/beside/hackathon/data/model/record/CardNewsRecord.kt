package com.beside.hackathon.data.model.record

data class CardNewsRecord(
    val newsId:Long,
    val takenTime: Int,
    val publishedDate: String,
    val urls : List<String>
)