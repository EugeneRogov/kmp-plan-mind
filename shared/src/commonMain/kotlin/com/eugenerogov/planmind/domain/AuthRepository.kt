package com.eugenerogov.planmind.domain

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.SignIn

interface AuthRepository {
    suspend fun signIn(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, SignIn>

    suspend fun register(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, SignIn>

    suspend fun logout(): Either<String, String>
}
