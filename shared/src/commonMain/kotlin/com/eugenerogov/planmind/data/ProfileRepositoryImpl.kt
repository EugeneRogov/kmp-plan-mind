package com.eugenerogov.planmind.data

import arrow.core.Either
import arrow.core.flatMap
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.ProfileRepository
import com.eugenerogov.planmind.domain.entities.SignIn
import com.eugenerogov.planmind.domain.entities.profile.UserProfile

class ProfileRepositoryImpl(

) : ProfileRepository {
    override suspend fun getUserProfile(): Either<Failure, UserProfile> {
        TODO("Not yet implemented")
    }
}
