package com.beside.hackathon.data.repository.home

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.home.SchoolRankScore
import com.beside.hackathon.data.model.home.SchoolRanking
import com.beside.hackathon.data.model.home.TotalRankScore
import com.beside.hackathon.data.model.home.TotalRanking
import com.beside.hackathon.data.model.home.UserProfile
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSchoolRanking(interest: String): SchoolRanking{
        return SchoolRanking(
            SchoolRankScore(1, "서울대학교", 100.0),
            listOf(
                SchoolRankScore(2, "서울대학교", 100.0),
                SchoolRankScore(3, "서울대학교", 100.0),
                SchoolRankScore(4, "서울대학교", 100.0),
            )
        )
        return apiService.getSchoolRanking(interest)
    }

    suspend fun getTotalRanking(interest: String) : TotalRanking{
        return TotalRanking(
            TotalRankScore(1, "김민수", "서울대학교", 100.0),
            listOf(
                TotalRankScore(2, "김민수", "서울대학교", 100.0),
                TotalRankScore(3, "김민수", "서울대학교", 100.0),
                TotalRankScore(4, "김민수", "서울대학교", 100.0),
            )
        )
        return apiService.getTotalRanking(interest)
    }

    suspend fun getProfile() : UserProfile {
        return apiService.getProfile()
    }
}