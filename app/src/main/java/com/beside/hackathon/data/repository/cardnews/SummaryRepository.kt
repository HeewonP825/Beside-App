package com.beside.hackathon.data.repository.cardnews

import com.beside.hackathon.data.api.ApiService
import javax.inject.Inject

class SummaryRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTodaySummary(): String {
        val response = apiService.getTodaySummary()
        return response.contentUrls
    }
}