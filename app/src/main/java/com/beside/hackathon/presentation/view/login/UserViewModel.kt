package com.beside.hackathon.presentation.view.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beside.hackathon.data.repository.user.TokenRepository
import com.beside.hackathon.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : ViewModel(){


    private val _isLogin = mutableStateOf(false)
    val isLogin : State<Boolean> = _isLogin

    init {
        tokenRepository.getAccessToken()?.let {
            _isLogin.value = true
        }
    }
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val jwtToken = userRepository.login(username, password)
                tokenRepository.setAccessToken(jwtToken.accessToken)
                tokenRepository.setRefreshToken(jwtToken.refreshToken)
                _isLogin.value = true
            }catch (e: Exception){
                Log.d("user", e.toString())
            }
        }
    }

}

