package com.beside.hackathon.data.model.home

data class SchoolRanking(
    val myRanking: SchoolRankingScore,
    val rankingUsers: List<SchoolRankingScore>
)

data class SchoolRankingScore(
    val rank: Int,
    val schoolName: String,
    val score: Double,
)