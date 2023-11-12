package com.beside.hackathon.presentation.view.cardnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.repository.CardNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardNewsViewModel @Inject constructor(
    private val cardNewsRepository: CardNewsRepository
) : ViewModel() {
    private val _cardNewsUrls = MutableLiveData<List<String>>()
    val cardNewsUrls: LiveData<List<String>> = _cardNewsUrls

    init {
        loadCardNewsUrls()
    }

    private fun loadCardNewsUrls() {
        viewModelScope.launch {
            try {
                val urls = cardNewsRepository.getTodayCardNews()
                _cardNewsUrls.value = urls
            } catch (e: Exception) {
                // 오류 처리
                Log.e("CardNewsViewModel", "Error loading card news: ${e.message}", e)
            }
        }
    }
}
