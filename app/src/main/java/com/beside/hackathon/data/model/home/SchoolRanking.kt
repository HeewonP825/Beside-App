package com.beside.hackathon.data.model.home

data class SchoolRanking(
    val myRanking: SchoolRankScore,
    val rankingUsers: List<SchoolRankScore>
)

data class SchoolRankScore(
    val rank: Int,
    val schoolName: String,
    val score: Double,
)