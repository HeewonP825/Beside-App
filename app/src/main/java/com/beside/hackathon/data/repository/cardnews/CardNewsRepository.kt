package com.beside.hackathon.data.repository

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.cardnews.CardNewsUrls
import javax.inject.Inject

class CardNewsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTodayCardNews(): List<String> {
        val response = apiService.getTodayCardNews()
        return response.contentUrls
    }
}

