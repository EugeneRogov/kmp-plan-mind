package com.eugenerogov.planmind.di

import com.arkivanov.decompose.ComponentContext
import com.eugenerogov.planmind.viewmodel.LoginViewModel
import com.eugenerogov.planmind.viewmodel.LoginViewModelImpl
import com.eugenerogov.planmind.viewmodel.ProfileViewModel
import com.eugenerogov.planmind.viewmodel.ProfileViewModelImpl
import com.eugenerogov.planmind.viewmodel.RegisterViewModel
import com.eugenerogov.planmind.viewmodel.RegisterViewModelImpl
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

    // Provide LoginViewModel with constructor parameters coming from parametersOf()
    factory<LoginViewModel> { params ->
        // 1-й параметр — ComponentContext (обязателен)
        val componentContext = params[0] as ComponentContext
        // 2-й, 3-й, 4-й параметры — коллбэки (опциональны)
        val onLoginSuccess: () -> Unit = if (params.size() > 1) params[1] as () -> Unit else {
            {}
        }
        val onNavigateToForgotPassword: () -> Unit =
            if (params.size() > 2) params[2] as () -> Unit else {
                {}
            }
        val onNavigateToNetworkSettings: () -> Unit =
            if (params.size() > 3) params[3] as () -> Unit else {
                {}
            }

        LoginViewModelImpl(
            componentContext = componentContext,
            onLoginSuccess = onLoginSuccess,
            onNavigateToForgotPassword = onNavigateToForgotPassword,
            onNavigateToNetworkSettings = onNavigateToNetworkSettings
        )
    }

    // Provide RegisterViewModel with constructor parameters coming from parametersOf()
    factory<RegisterViewModel> { params ->
        // 1-й параметр — ComponentContext (обязателен)
        val componentContext = params[0] as ComponentContext
        // 2-й, 3-й параметры — коллбэки (опциональны)
        val onRegisterSuccess: () -> Unit = if (params.size() > 1) params[1] as () -> Unit else {
            {}
        }
        val onNavigateToLogin: () -> Unit = if (params.size() > 2) params[2] as () -> Unit else {
            {}
        }

        RegisterViewModelImpl(
            componentContext = componentContext,
            onRegisterSuccess = onRegisterSuccess,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}
