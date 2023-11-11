package com.beside.hackathon.presentation.view.quizhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.beside.hackathon.data.model.quizhistory.QuizHistory
import com.beside.hackathon.data.repository.quizhistory.QuizHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizHistoryViewModel @Inject constructor(
    private val repository: QuizHistoryRepository
) : ViewModel(){
    private val _history = MutableStateFlow<PagingData<QuizHistory>>(PagingData.empty())
    val history: StateFlow<PagingData<QuizHistory>> = _history.asStateFlow()
    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getPagingData()
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect {
                        _history.value = it
                    }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}