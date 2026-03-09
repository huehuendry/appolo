package com.hendry.appolo.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository {

    private val auth = FirebaseAuth.getInstance()

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(email, password)
                .await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

}