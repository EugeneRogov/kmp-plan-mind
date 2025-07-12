package com.eugenerogov.planmind.domain

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse

interface AuthRepository {
    suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, LoginResponse>

    suspend fun register(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, LoginResponse>

    suspend fun logout(): Either<String, String>

    suspend fun isAuthenticated(): Boolean
}
