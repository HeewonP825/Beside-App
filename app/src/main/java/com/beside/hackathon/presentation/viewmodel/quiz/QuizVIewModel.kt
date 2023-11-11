package com.beside.hackathon.presentation.viewmodel.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.model.quiz.Quiz
import com.beside.hackathon.data.repository.quiz.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizVIewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel(){


    private val _quiz = MutableStateFlow(Quiz(0,"", emptyList()))

    val quiz: StateFlow<Quiz> = _quiz.asStateFlow()

    init {
        getTodayQuiz()
    }

    fun getTodayQuiz(){
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getTodayQuiz()
            }.onSuccess {
                _quiz.value = it
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}