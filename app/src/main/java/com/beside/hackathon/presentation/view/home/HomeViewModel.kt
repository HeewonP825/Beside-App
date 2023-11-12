package com.beside.hackathon.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.model.home.TotalRankScore
import com.beside.hackathon.data.model.home.SchoolRankScore
import com.beside.hackathon.data.model.home.UserProfile
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

    private val _dataForTotalRankingFragment = MutableLiveData<List<TotalRankScore>>()
    val dataForTotalRankingFragment: LiveData<List<TotalRankScore>> = _dataForTotalRankingFragment

    private val _dataForUnivRankingFragment = MutableLiveData<List<SchoolRankScore>>()
    val dataForUnivRankingFragment: LiveData<List<SchoolRankScore>> = _dataForUnivRankingFragment

    private val _profile = MutableLiveData<UserProfile>()
    val profile: LiveData<UserProfile> = _profile

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch{
            val totalResp = homeRepository.getTotalRanking()

            val totalList = mutableListOf<TotalRankScore>()
            totalList.add(totalResp.myRanking)
            totalList.addAll(totalResp.rankingUsers)
            _dataForTotalRankingFragment.value = totalList

            val schoolResp = homeRepository.getSchoolRanking()
            val schoolList = mutableListOf<SchoolRankScore>()
            schoolList.add(schoolResp.myRanking)
            schoolList.addAll(schoolResp.rankingUsers)
            _dataForUnivRankingFragment.value = schoolList
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            try {
                val userProfile = homeRepository.getProfile()
                _profile.value = userProfile
            } catch (e: Exception) {
                // 에러 처리
            }
        }
    }

    // 현재 페이지를 설정하는 메서드
    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    // 필요한 경우 추가 데이터 로드 및 처리 로직
}

