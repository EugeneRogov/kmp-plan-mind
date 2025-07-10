package com.eugenerogov.planmind.domain.entities.profile

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String?,
    val createdAt: String,
    val preferences: Map<String, String>
)