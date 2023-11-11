package com.beside.hackathon.data.repository.common

import androidx.paging.PagingData
import com.beside.hackathon.data.model.common.ModelWithId
import com.beside.hackathon.data.model.common.OffsetPagination
import kotlinx.coroutines.flow.Flow

interface PagingRepository<T : Any> {

    suspend fun paginate(
        page: Int,
    ) : OffsetPagination<T>

    fun getPagingData(page: Int) : Flow<PagingData<T>>
}