package com.eugenerogov.planmind.viewmodel.auth

import com.eugenerogov.planmind.viewmodel.UiState

data class LoginUiState(
    val inProgress: Boolean = false,
    val login: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = true,
    val isPasswordEnabled: Boolean = true,
    val isCheckLoginIn: Boolean = false,
    val isCheckLoginInEnabled: Boolean = true,
    val errorMessage: String = "",
    val isNetworkSettings: Boolean = false,
    val debugMenuExpanded: Boolean = false
) : UiState {
    companion object {
        fun preview() = LoginUiState(
            inProgress = false,
            isLoginEnabled = true,
            login = "9812378123"
        )
    }
}
