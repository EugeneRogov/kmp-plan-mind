package com.eugenerogov.planmind.data.remote

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.SignIn
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import com.eugenerogov.planmind.data.remote.core.Endpoint
import kotlinx.serialization.Serializable

interface AuthService {

    suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, SignIn>
}

class AuthServiceImpl(private val client: HttpClient) : AuthService {
    override suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, SignIn> {
        TODO("Not yet implemented")
    }

}