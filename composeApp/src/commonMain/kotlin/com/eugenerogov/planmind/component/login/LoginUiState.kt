package com.eugenerogov.planmind.component.login

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
) {
    companion object {
        fun preview() = LoginUiState(
            inProgress = false,
            isLoginEnabled = true,
            login = "9812378123"
        )
    }
}