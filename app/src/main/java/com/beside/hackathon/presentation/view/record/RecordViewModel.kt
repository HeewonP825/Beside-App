package com.beside.hackathon.presentation.view.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.beside.hackathon.data.model.quizhistory.QuizHistory
import com.beside.hackathon.data.model.record.CardNewsRecord
import com.beside.hackathon.data.model.record.SummaryRecord
import com.beside.hackathon.data.repository.quizhistory.QuizHistoryRepository
import com.beside.hackathon.data.repository.record.CardNewsRecordRepository
import com.beside.hackathon.data.repository.record.SummariesRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val summariesRecordRepository: SummariesRecordRepository,
    private val cardNewsRecordRepository: CardNewsRecordRepository,
): ViewModel() {

    private val _summaryRecords = MutableStateFlow<PagingData<SummaryRecord>>(PagingData.empty())
    val summaryRecords: StateFlow<PagingData<SummaryRecord>> = _summaryRecords.asStateFlow()

    private val _cardNewsRecords = MutableStateFlow<PagingData<CardNewsRecord>>(PagingData.empty())
    val cardNewsRecords: StateFlow<PagingData<CardNewsRecord>> = _cardNewsRecords.asStateFlow()

    init {
        getSummaryRecords()
        getCardNewsRecords()
    }

    private fun getSummaryRecords() {
        viewModelScope.launch {
            kotlin.runCatching {
                summariesRecordRepository.getPagingData()
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect {
                        _summaryRecords.value = it
                    }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private fun getCardNewsRecords() {
        viewModelScope.launch {
            kotlin.runCatching {
                cardNewsRecordRepository.getPagingData()
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect {
                        _cardNewsRecords.value = it
                    }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}