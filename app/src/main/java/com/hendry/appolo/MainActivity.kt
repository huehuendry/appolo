package com.hendry.appolo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hendry.appolo.ui.HomeScreen
import com.hendry.appolo.ui.LoginScreen
import com.hendry.appolo.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: LoginViewModel = viewModel()

            val loginResult by viewModel.loginResult.collectAsState()

            when (loginResult) {

                true -> {
                    HomeScreen()
                }

                false -> {

                    Toast.makeText(
                        this,
                        "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()

                    LoginScreen { email, password ->
                        viewModel.login(email, password)
                    }
                }

                else -> {

                    LoginScreen { email, password ->
                        viewModel.login(email, password)
                    }

                }
            }

        }
    }
}