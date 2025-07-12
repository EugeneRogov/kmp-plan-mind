package com.eugenerogov.planmind.data

import arrow.core.Either
import arrow.core.flatMap
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.data.remote.AuthService
import com.eugenerogov.planmind.data.remote.ProfileService
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class AuthRepositoryImpl(

) : AuthRepository, KoinComponent {

    private val authService: AuthService by inject()

    override suspend fun signIn(
        username: String,
        password: String
    ) = authService.signIn(username, password)

    override suspend fun register(
        stayLogged: Boolean,
        username: String,
        password: String
    ): Either<Failure, LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Either<String, String> {
        TODO("Not yet implemented")
    }

    override suspend fun isAuthenticated(): Boolean {
        // TODO: Implement proper authentication check
        // For now, return false as placeholder
        return false
    }
}
