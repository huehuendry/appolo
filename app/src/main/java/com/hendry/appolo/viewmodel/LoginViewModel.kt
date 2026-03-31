package com.hendry.appolo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hendry.appolo.auth.FirebaseAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = FirebaseAuthRepository()

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult = _loginResult.asStateFlow()

    init {
        checkSession()
    }

    fun checkSession() {
        val user = repo.getCurrentUser()
        _loginResult.value = user != null
    }

    fun login(email: String, password: String) {

        viewModelScope.launch {

            val result = repo.login(email, password)

            _loginResult.value = result.isSuccess

        }

    }

    fun logout() {
        repo.logout()
        _loginResult.value = false
    }
}
