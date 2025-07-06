package com.eugenerogov.planmind.data

import arrow.core.Either
import arrow.core.flatMap
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.entities.SignIn

class AuthRepositoryImpl(

) : AuthRepository {
    override suspend fun signIn(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Either<String, String> {
        TODO("Not yet implemented")
    }

}
