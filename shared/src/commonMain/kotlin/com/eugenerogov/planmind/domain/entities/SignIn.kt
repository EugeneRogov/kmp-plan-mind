package com.eugenerogov.planmind.domain.entities

data class SignIn(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String
)
