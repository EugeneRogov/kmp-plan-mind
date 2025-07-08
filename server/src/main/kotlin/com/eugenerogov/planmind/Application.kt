package com.eugenerogov.planmind

import com.eugenerogov.planmind.domain.entities.profile.UserProfile
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

const val SERVER_PORT = 8080

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        get("/plan-mind") {
            val data = mapOf("names" to listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Helen", "Isaac", "Julia"))
            call.respond(data)
        }

        get("/user/profile") {
            val profile = UserProfile(
                id = "user123",
                name = "John Doe",
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
}
