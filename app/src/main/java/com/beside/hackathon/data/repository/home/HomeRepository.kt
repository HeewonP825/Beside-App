package com.beside.hackathon.data.repository.home

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.home.SchoolRanking
import com.beside.hackathon.data.model.home.TotalRanking
import com.beside.hackathon.data.model.home.UserProfile
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSchoolRanking(): SchoolRanking{
        return apiService.getSchoolRanking()
    }

    suspend fun getTotalRanking() : TotalRanking{
        return apiService.getTotalRanking()
    }

    suspend fun getProfile() : UserProfile {
        return apiService.getProfile()
    }
}