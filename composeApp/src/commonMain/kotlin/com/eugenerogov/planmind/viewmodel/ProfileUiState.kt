package com.eugenerogov.planmind.viewmodel

data class ProfileUiState(
    val isLoading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val avatar: String? = null,
    val errorMessage: String = "",
    val isEditing: Boolean = false,
    val isSaving: Boolean = false
) {
    companion object {
        fun preview() = ProfileUiState(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
            avatar = "https://example.com/avatar.jpg"
        )
    }
}