package com.eugenerogov.planmind.domain

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.profile.UserProfileResponse

interface ProfileRepository {
    suspend fun getUserProfile(): Either<Failure, UserProfileResponse>
}
