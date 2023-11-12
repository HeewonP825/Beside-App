package com.beside.hackathon.data.model.record

data class SummaryRecord(
    val summaryId: Long,
    val takenTime: Int,
    val publishedDate: String,
    val subject: String,
    val content: String,
)
