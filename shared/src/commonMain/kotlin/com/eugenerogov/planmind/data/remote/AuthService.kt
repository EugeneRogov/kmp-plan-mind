package com.eugenerogov.planmind.data.remote

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import com.eugenerogov.planmind.data.remote.core.Endpoint
import com.eugenerogov.planmind.domain.entities.auth.LoginRequest
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse
import kotlinx.serialization.Serializable

interface AuthService {

    suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, LoginResponse>
}

class AuthServiceImpl(private val client: HttpClient) : AuthService {
    //    override suspend fun signIn(
//        username: String,
//        password: String
//    ): Either<Failure, LoginResponse> {
//        return try {
//            val response: Map<String, String> =
//                client.post("${Endpoint.BASE_URL}${Endpoint.USER_SIGN_IN}") {
//                    setBody(LoginRequest(username, password))
//                }.body()
//
//            val token = response["token"]
//            if (token != null) {
//                Either.Right(
//                    SignIn(
//                        accessToken = token,
//                        expiresIn = 600,
//                        refreshToken = ""
//                    )
//                )
//            } else {
//                Either.Left(Failure.ServerError(response["error"] ?: "Unknown error"))
//            }
//        } catch (e: Exception) {
//            Either.Left(Failure.NetworkError(e.message ?: "Unknown error"))
//        }
//    }
    override suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, LoginResponse> {
        TODO("Not yet implemented")
    }
}