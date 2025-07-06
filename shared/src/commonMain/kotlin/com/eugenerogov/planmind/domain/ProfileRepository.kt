package com.eugenerogov.planmind.domain

import arrow.core.Either
import com.eugenerogov.planmind.domain.entities.profile.ProfileGeneralInfo
import com.eugenerogov.planmind.Failure

interface ProfileRepository {
    suspend fun getGeneralInfo(): Either<Failure, ProfileGeneralInfo>
}
