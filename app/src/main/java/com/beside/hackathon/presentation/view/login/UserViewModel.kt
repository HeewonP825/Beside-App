package com.beside.hackathon.presentation.view.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.model.user.Interest
import com.beside.hackathon.data.repository.user.TokenRepository
import com.beside.hackathon.data.repository.user.UserRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) : ViewModel(){


    private val _isLogin = mutableStateOf(false)
    val isLogin : State<Boolean> = _isLogin

    private val _isIdValid = mutableStateOf(false)
    val isIdValid : State<Boolean> = _isIdValid

    private val _isNicknameValid = mutableStateOf(false)
    val isNicknameValid : State<Boolean> = _isNicknameValid

    init {
        tokenRepository.getAccessToken()?.let {
            _isLogin.value = true
        }
    }
    fun login(
        username: String,
        password: String,
    ) {
        val fcmToken = tokenRepository.getFcmToken()
        Log.d("user", "fcmToken is ${fcmToken}!!")


        viewModelScope.launch(Dispatchers.IO) {
            try{
                val jwtToken = userRepository.login(username, password,fcmToken!!)
                tokenRepository.setAccessToken(jwtToken.accessToken)
                tokenRepository.setRefreshToken(jwtToken.refreshToken)
                _isLogin.value = true
            }catch (e: Exception){
                Log.d("user", e.toString())
            }
        }
    }

    suspend fun idValidCheck(id: String):Boolean{
        kotlin.runCatching {
            userRepository.idValidation(id)
        }.onSuccess {
            _isIdValid.value = !it
            return !it
        }.onFailure {
            it.printStackTrace()
        }
        return false
    }
    suspend fun nicknameValidCheck(nickname: String) : Boolean{
        kotlin.runCatching {
            userRepository.nicknameValidation(nickname)
        }.onSuccess {
            _isNicknameValid.value = !it
            return !it
        }.onFailure {
            it.printStackTrace()
        }
        return false
    }
    fun signUp(id: String, password: String, collage: String, nickName: String, interest: Interest) {
        if(!_isIdValid.value) throw Exception("id is not valid")
        if(!_isNicknameValid.value) throw Exception("nickname is not valid")
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.signUp(id, password,nickName,collage,interest)
            }.onSuccess {
                login(id, password)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}

