package com.beside.hackathon.presentation.view.cardnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardNewsViewModel : ViewModel() {
    private val _cardNewsUrls = MutableLiveData<List<String>>()
    val cardNewsUrls: LiveData<List<String>> = _cardNewsUrls

    fun loadCardNewsUrls() {
        // 서버에서 데이터를 로드하는 로직 구현
        // 예시로 하드코딩된 URL 리스트 사용
        _cardNewsUrls.value = listOf("https://picsum.photos/300/350", "https://picsum.photos/300/350", "https://picsum.photos/300/350")
    }

}