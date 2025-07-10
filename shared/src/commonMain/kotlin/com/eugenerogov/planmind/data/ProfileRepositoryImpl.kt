package com.eugenerogov.planmind.data

import com.eugenerogov.planmind.data.remote.ProfileService
import com.eugenerogov.planmind.domain.ProfileRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileRepositoryImpl(
) : ProfileRepository, KoinComponent {
    private val profileService: ProfileService by inject()

    override suspend fun getUserProfile() = profileService.getUserProfile()
//        .mapLeft {
//            when (it) {
//                is Failure.NetworkError -> Failure.NetworkError(it.message)
//                else -> Failure.UnknownHostException()
//            }
//    }
}
