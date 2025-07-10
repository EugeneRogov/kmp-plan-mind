package com.eugenerogov.planmind.domain.entities.auth

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(val token: String)