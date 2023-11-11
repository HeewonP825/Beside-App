package com.beside.hackathon.presentation.view.quizhistory

import androidx.lifecycle.ViewModel
import com.beside.hackathon.data.repository.quizhistory.QuizHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizHistoryViewModel @Inject constructor(
    private val repository: QuizHistoryRepository
) : ViewModel(){

}