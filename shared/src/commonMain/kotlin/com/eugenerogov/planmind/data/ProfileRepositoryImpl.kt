package com.eugenerogov.planmind.data

import arrow.core.Either
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.data.remote.ProfileService
import com.eugenerogov.planmind.domain.ProfileRepository
import com.eugenerogov.planmind.domain.entities.profile.UserProfile
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileRepositoryImpl(
) : ProfileRepository, KoinComponent {
    private val profileService: ProfileService by inject()
    override suspend fun getUserProfile(): Either<Failure, UserProfile> {
        TODO("Not yet implemented")
    }
}
