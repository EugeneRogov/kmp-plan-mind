package com.eugenerogov.planmind.viewmodel

import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_EMAIL
import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_PASSWORD

data class RegisterUiState(
    val inProgress: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val stayLogged: Boolean = false,
    val isUsernameEnabled: Boolean = true,
    val isEmailEnabled: Boolean = true,
    val isPasswordEnabled: Boolean = true,
    val isConfirmPasswordEnabled: Boolean = true,
    val isStayLoggedEnabled: Boolean = true,
    val errorMessage: String = "",
    val debugMenuExpanded: Boolean = false
) {
    companion object {
        fun preview() = RegisterUiState(
            inProgress = false,
            isUsernameEnabled = true,
            isEmailEnabled = true,
            email = DEBUG_EMAIL,
            password = DEBUG_PASSWORD,
            confirmPassword = DEBUG_PASSWORD,
            username = "testuser"
        )
    }
}