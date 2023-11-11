package com.beside.hackathon.data.api

import com.beside.hackathon.data.model.cardnews.CardNewsUrls
import com.beside.hackathon.data.model.common.OffsetPagination
import com.beside.hackathon.data.model.home.SchoolRanking
import com.beside.hackathon.data.model.home.TotalRanking
import com.beside.hackathon.data.model.home.UserProfile
import com.beside.hackathon.data.model.quiz.Quiz
import com.beside.hackathon.data.model.quiz.QuizSubmitRequest
import com.beside.hackathon.data.model.quiz.QuizSubmitResponse
import com.beside.hackathon.data.model.quizhistory.CorrectQuiz
import com.beside.hackathon.data.model.quizhistory.QuizHistory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @GET("quiz/today")
    suspend fun getTodayQuiz(): Quiz

    @POST("quiz/submit")
    suspend fun submitQuiz(
        @Body quizSubmitRequest: QuizSubmitRequest
    ): QuizSubmitResponse


    @GET("cardnews/today")
    suspend fun getTodayCardNews(): List<CardNewsUrls>


    @GET("profile")
    suspend fun getProfile(): UserProfile

    @GET("rank/total")
    suspend fun getTotalRanking(): TotalRanking

    @GET("rank/school")
    suspend fun getSchoolRanking(): SchoolRanking

    @GET("record/quiz")
    suspend fun getQuizHistory(): OffsetPagination<QuizHistory>

    @GET("quiz/{quizId}")
    suspend fun getQuizDetail(
        @Path("quizId") quizId: Long
    ): CorrectQuiz

}