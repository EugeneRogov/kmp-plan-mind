package com.eugenerogov.planmind.domain.entities.profile

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
