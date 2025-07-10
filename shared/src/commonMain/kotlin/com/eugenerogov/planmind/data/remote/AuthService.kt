package com.eugenerogov.planmind.data.remote

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.data.remote.core.Endpoint
import com.eugenerogov.planmind.domain.entities.auth.LoginRequest
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

interface AuthService {

    suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, LoginResponse>
}

class AuthServiceImpl(private val client: HttpClient) : AuthService {
    override suspend fun signIn(
        username: String,
        password: String
    ): Either<Failure, LoginResponse> {
        return try {
            val response = client.post(Endpoint.USER_SIGN_IN) {
                setBody(LoginRequest(username, password))
            }
            val bodyStr = response.bodyAsText()
            try {
                val loginResponse = Json.decodeFromString(LoginResponse.serializer(), bodyStr)
                Either.Right(loginResponse)
            } catch (_: Exception) {
                Either.Left(
                    Failure.ServerError(
                        try {
                            Json.parseToJsonElement(bodyStr).jsonObject["error"]?.jsonPrimitive?.content
                                ?: "Unknown error"
                        } catch (_: Exception) {
                            "Unknown error"
                        }
                    )
                )
            }
        } catch (e: Exception) {
            println("Login failed: ${e.message}")
            Either.Left(Failure.NetworkError(e.message ?: "Unknown error"))
        }
    }
}