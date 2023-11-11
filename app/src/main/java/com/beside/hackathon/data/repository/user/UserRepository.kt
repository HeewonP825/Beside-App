package com.beside.hackathon.data.repository.user

import com.beside.hackathon.data.api.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
}