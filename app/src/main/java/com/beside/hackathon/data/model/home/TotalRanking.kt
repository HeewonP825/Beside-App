package com.beside.hackathon.data.model.home

data class TotalRanking(
    val myRanking: TotalRankScore,
    val rankingUsers: List<TotalRankScore>
)

data class TotalRankScore(
    val rank: Int,
    val nickname: String,
    val schoolName: String,
    val score: Double,
)
