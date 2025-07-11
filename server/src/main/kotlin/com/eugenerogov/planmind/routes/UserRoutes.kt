package com.eugenerogov.planmind.routes

import com.eugenerogov.planmind.data.remote.core.Endpoint.USER_ME_PROFILE
import com.eugenerogov.planmind.domain.entities.profile.UserProfileResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    get(USER_ME_PROFILE) {
        val profile = UserProfileResponse(
            id = "user123",
            name = "John123 Doeу2у",
            email = "john.doe@example.com",
            avatar = "https://example.com/avatar.jpg",
            createdAt = "2024-01-01T00:00:00Z",
            preferences = mapOf(
                "theme" to "dark",
                "language" to "en",
                "notifications" to "true"
            )
        )
        call.respond(profile)
    }
}
