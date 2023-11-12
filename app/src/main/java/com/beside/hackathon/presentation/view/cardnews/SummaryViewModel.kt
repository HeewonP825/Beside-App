package com.beside.hackathon.presentation.view.cardnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.repository.CardNewsRepository
import com.beside.hackathon.data.repository.cardnews.SummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val summaryRepository: SummaryRepository
) : ViewModel() {
    private val _summaryUrls = MutableLiveData<String>()
    val summaryUrls: LiveData<String> = _summaryUrls

    init {
        loadSummaryUrls()
    }

    fun loadSummaryUrls() {
        viewModelScope.launch {
            try {
                val urls = summaryRepository.getTodaySummary()
                _summaryUrls.value = urls
            } catch (e: Exception) {
                // 오류 처리
                Log.e("SummaryViewModel", "Error loading card news: ${e.message}", e)
            }
        }
    }
}
