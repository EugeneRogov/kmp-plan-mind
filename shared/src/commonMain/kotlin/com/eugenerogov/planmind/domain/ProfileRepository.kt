package com.eugenerogov.planmind.domain

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.entities.profile.UserProfile

interface ProfileRepository {
    suspend fun getGeneralInfo(): Either<Failure, UserProfile>
}
