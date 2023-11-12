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
//        return SchoolRanking(
//            SchoolRankScore(3, "경북대학교", 87.5),
//            listOf(
//                SchoolRankScore(1, "서울대학교", 98.9),
//                SchoolRankScore(2, "연세대학교", 92.1),
//                SchoolRankScore(4, "한양대학교", 85.5),
//            )
//        )
        return apiService.getSchoolRanking(interest)
    }

    suspend fun getTotalRanking(interest: String) : TotalRanking{
        return apiService.getTotalRanking(interest)
    }

    suspend fun getProfile() : UserProfile {
        return apiService.getProfile()
    }
}