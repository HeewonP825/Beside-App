package com.beside.hackathon.data.repository.record

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.common.OffsetPagination
import com.beside.hackathon.data.model.record.SummaryRecord
import com.beside.hackathon.data.repository.common.OffsetPagingSource
import com.beside.hackathon.data.repository.common.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummariesRecordRepository @Inject constructor(
    private val apiService: ApiService,
) : PagingRepository<SummaryRecord> {
    override suspend fun paginate(page: Int): OffsetPagination<SummaryRecord> {
        return apiService.getSummariesRecord(page)
    }

    override fun getPagingData(): Flow<PagingData<SummaryRecord>> {
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