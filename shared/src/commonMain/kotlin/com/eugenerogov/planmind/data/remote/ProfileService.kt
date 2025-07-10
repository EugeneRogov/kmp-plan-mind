package com.eugenerogov.planmind.data.remote

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.profile.UserProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.eugenerogov.planmind.data.remote.core.Endpoint.USER_PROFILE

interface ProfileService {

    suspend fun getUserProfile(): Either<Failure, UserProfileResponse>

}

class ProfileServiceImpl(private val client: HttpClient) : ProfileService {

    override suspend fun getUserProfile(): Either<Failure, UserProfileResponse> {
        return try {
            val profile: UserProfileResponse = client.get(USER_PROFILE).body()
            Either.Right(profile)
        } catch (e: Exception) {
            Either.Left(Failure.NetworkError(e.message ?: "Unknown error"))
        }
    }

}