package com.beside.hackathon.presentation.view.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.model.quiz.Answer
import com.beside.hackathon.data.model.quiz.Quiz
import com.beside.hackathon.data.model.quiz.QuizSubmitResponse
import com.beside.hackathon.data.repository.quiz.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel(){


    private val _quiz = MutableStateFlow(Quiz(0,"", emptyList()))
    val quiz: StateFlow<Quiz> = _quiz.asStateFlow()

    private val _quizSubmitNumbers : MutableStateFlow<List<Answer>> = MutableStateFlow(emptyList())
    val quizSubmitNumbers: StateFlow<List<Answer>> = _quizSubmitNumbers


    init {
        getTodayQuiz()
    }

    fun getTodayQuiz(){
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getTodayQuiz()
            }.onSuccess {
                _quiz.value = it
                _quizSubmitNumbers.value = it.questions.map {question->
                    Answer(question.questionId,-1)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun selectOption(questionId: Long,optionId: Int){
        _quizSubmitNumbers.value = _quizSubmitNumbers.value.map { answer ->
            if(answer.questionId == questionId){
                Answer(questionId,optionId)
            }else{
                answer
            }
        }
    }


    suspend fun submitQuiz() : QuizSubmitResponse?{
        _quizSubmitNumbers.value.forEach{
            if(it.optionId == -1){
                return null
            }
        }
        Log.d("QuizViewModel", "submitQuiz: ${_quizSubmitNumbers.value}")

        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                kotlin.runCatching {
                    val response = repository.submitQuiz(
                        _quiz.value.quizId,_quizSubmitNumbers.value
                    )
                    response
                }.onSuccess {
                    continuation.resume(it) // 성공 시 결과를 반환
                }.onFailure { error ->
                    error.printStackTrace()
                    continuation.resume(null) // 실패 시 null을 반환
                }
            }
        }
    }
}