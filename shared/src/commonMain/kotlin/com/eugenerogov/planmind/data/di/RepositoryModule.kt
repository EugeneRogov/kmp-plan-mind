package com.eugenerogov.planmind.data.di

import com.eugenerogov.planmind.data.AuthRepositoryImpl
import com.eugenerogov.planmind.data.ProfileRepositoryImpl
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.ProfileRepository
import org.koin.dsl.module

val repositoryModule =
    module {

        single<AuthRepository> {
            AuthRepositoryImpl(

            )
        }

        single<ProfileRepository> {
            ProfileRepositoryImpl(

            )
        }
    }
