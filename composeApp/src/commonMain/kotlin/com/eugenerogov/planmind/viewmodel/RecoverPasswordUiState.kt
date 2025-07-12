package com.eugenerogov.planmind.viewmodel

import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_EMAIL

data class RecoverPasswordUiState(
    val inProgress: Boolean = false,
    val email: String = "",
    val isEmailEnabled: Boolean = true,
    val errorMessage: String = "",
    val successMessage: String = "",
    val debugMenuExpanded: Boolean = false
) {
    companion object {
        fun preview() = RecoverPasswordUiState(
            inProgress = false,
            isEmailEnabled = true,
            email = DEBUG_EMAIL
        )
    }
}