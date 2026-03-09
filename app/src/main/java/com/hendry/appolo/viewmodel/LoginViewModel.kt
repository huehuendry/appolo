package com.hendry.appolo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hendry.appolo.auth.FirebaseAuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = FirebaseAuthRepository()

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            val result = repository.login(email, password)

            result.onSuccess {

                println("Login Success")

            }.onFailure {

                println("Login Failed")

            }

        }

    }

}