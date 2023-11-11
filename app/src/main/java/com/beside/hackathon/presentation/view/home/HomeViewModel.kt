package com.beside.hackathon.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beside.hackathon.data.model.home.Ranking

class HomeViewModel : ViewModel() {

    // 현재 페이지를 저장하는 MutableLiveData
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val _dataForTotalRankingFragment = MutableLiveData<List<Ranking>>()
    val dataForTotalRankingFragment: LiveData<List<Ranking>> = _dataForTotalRankingFragment

    private val _dataForUnivRankingFragment = MutableLiveData<List<Ranking>>()
    val dataForUnivRankingFragment: LiveData<List<Ranking>> = _dataForUnivRankingFragment

    init {
        loadData()
    }

    private fun loadData() {
        // 예시 데이터
        _dataForTotalRankingFragment.value = listOf(/* ... 데이터 ... */)
        _dataForUnivRankingFragment.value = listOf(/* ... 데이터 ... */)
    }

    // 현재 페이지를 설정하는 메서드
    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    // 필요한 경우 추가 데이터 로드 및 처리 로직
}

