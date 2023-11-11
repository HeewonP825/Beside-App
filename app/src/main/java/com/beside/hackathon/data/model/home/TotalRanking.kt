package com.beside.hackathon.data.model.home

data class TotalRanking(
    val myRanking: RankScore,
    val rankingUsers: List<RankScore>
)

data class RankScore(
    val rank: Int,
    val name: String,
    val schoolName: String,
    val score: Double,
)
