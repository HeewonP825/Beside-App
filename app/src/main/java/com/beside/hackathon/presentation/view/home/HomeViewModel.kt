package com.beside.hackathon.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> get() = _currentPage

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }
}
