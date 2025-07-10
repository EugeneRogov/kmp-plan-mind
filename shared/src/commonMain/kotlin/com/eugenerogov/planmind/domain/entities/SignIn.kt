package com.eugenerogov.planmind.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class SignIn(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String
)
