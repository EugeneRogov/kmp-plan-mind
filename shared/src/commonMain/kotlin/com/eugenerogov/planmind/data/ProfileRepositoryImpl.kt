package com.eugenerogov.planmind.data

import arrow.core.Either
import arrow.core.flatMap
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.ProfileRepository
import com.eugenerogov.planmind.domain.entities.SignIn
import com.eugenerogov.planmind.domain.entities.profile.ProfileGeneralInfo

class ProfileRepositoryImpl(

) : ProfileRepository {
    override suspend fun getGeneralInfo(): Either<Failure, ProfileGeneralInfo> {
        TODO("Not yet implemented")
    }
}
