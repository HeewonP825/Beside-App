package com.beside.hackathon.data.model.common

data class OffsetPagination<T>(
    val hasNext: Boolean,
    val data: List<T>,
)