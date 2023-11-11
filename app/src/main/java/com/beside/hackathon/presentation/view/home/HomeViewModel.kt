package com.beside.hackathon.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.model.home.RankScore
import com.beside.hackathon.data.model.home.SchoolRanking
import com.beside.hackathon.data.model.home.SchoolRankingScore
import com.beside.hackathon.data.model.home.TotalRanking
import com.beside.hackathon.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    // 현재 페이지를 저장하는 MutableLiveData
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val _dataForTotalRankingFragment = MutableLiveData<List<RankScore>>()
    val dataForTotalRankingFragment: LiveData<List<RankScore>> = _dataForTotalRankingFragment

    private val _dataForUnivRankingFragment = MutableLiveData<List<SchoolRankingScore>>()
    val dataForUnivRankingFragment: LiveData<List<SchoolRankingScore>> = _dataForUnivRankingFragment

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch{
            val totalresp = homeRepository.getTotalRanking()
            val schoolResp = homeRepository.getSchoolRanking()
            val totalList =
                listOf(
                    totalresp.myRanking,
                    totalresp.rankingUsers,
                )
        }
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

