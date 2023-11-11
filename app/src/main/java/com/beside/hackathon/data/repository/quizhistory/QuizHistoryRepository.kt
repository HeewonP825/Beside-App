package com.beside.hackathon.data.repository.quizhistory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.common.OffsetPagination
import com.beside.hackathon.data.model.quizhistory.CorrectQuiz
import com.beside.hackathon.data.model.quizhistory.QuizHistory
import com.beside.hackathon.data.repository.common.OffsetPagingSource
import com.beside.hackathon.data.repository.common.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizHistoryRepository @Inject constructor(
    private val apiService: ApiService,
) : PagingRepository<QuizHistory>{


    suspend fun getCorrectQuiz(quizId: Long): CorrectQuiz {
        return apiService.getCorrectQuiz(quizId)
    }

    override suspend fun paginate(page: Int): OffsetPagination<QuizHistory> {
        return apiService.getQuizHistory()
    }

    override fun getPagingData(): Flow<PagingData<QuizHistory>> {
        val pagingSourceFactory = { OffsetPagingSource(0, this) }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}