package com.eugenerogov.planmind.di

import com.eugenerogov.planmind.domain.usecases.SignInLoginUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val useCasesModule =
    module {
        // region Task

        // endregion

        // region Auth
        factory {
            SignInLoginUseCase(
//                repository = get(),
                dispatcher = Dispatchers.Default
            )
        }
        // endregion

    }
