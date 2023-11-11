package com.beside.hackathon.data.repository.common

import androidx.paging.PagingSource
import androidx.paging.PagingState

class OffsetPagingSource<T : Any>(
    private val page: Int,
    private val repository: PagingRepository<T>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val response = repository.paginate(
                page= page
            )

            LoadResult.Page(
                data = response.data,
                prevKey = params.key,
                nextKey = if (response.hasNext) {
                    page + 1
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}