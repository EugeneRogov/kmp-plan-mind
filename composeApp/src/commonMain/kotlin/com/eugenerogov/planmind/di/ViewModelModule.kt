package com.eugenerogov.planmind.di

import com.arkivanov.decompose.ComponentContext
import com.eugenerogov.planmind.viewmodel.ProfileViewModel
import com.eugenerogov.planmind.viewmodel.ProfileViewModelImpl
import org.koin.dsl.module

/**
 * Common ViewModel Koin module that works for Android, iOS, WASM.
 * Requires a [ComponentContext] to be passed via parametersOf when resolving.
 * The second optional parameter is a callback that will be invoked after profile saved.
 */
val viewModelModule = module {
    // Provide ProfileViewModel with constructor parameters coming from parametersOf()
    factory<ProfileViewModel> { params ->
        // 1-й параметр обязателен – ComponentContext (Decompose lifecycle holder)
        val componentContext = params[0] as ComponentContext
        // 2-й параметр опционален – коллбэк после сохранения профиля
        val onProfileSaved: () -> Unit = if (params.size() > 1) {
            params[1] as () -> Unit
        } else {
            {}
        }

        ProfileViewModelImpl(
            componentContext = componentContext,
            onProfileSaved = onProfileSaved
        )
    }
}
