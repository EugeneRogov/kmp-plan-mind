package com.eugenerogov.planmind

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.eugenerogov.planmind.data.remote.core.Endpoint.HOST
import com.eugenerogov.planmind.data.remote.core.Endpoint.MIND_PLAN
import com.eugenerogov.planmind.data.remote.core.Endpoint.SERVER_PORT
import com.eugenerogov.planmind.data.remote.core.Endpoint.USER_PROFILE
import com.eugenerogov.planmind.data.remote.core.Endpoint.USER_SIGN_IN
import com.eugenerogov.planmind.domain.LoginRequest
import com.eugenerogov.planmind.domain.entities.profile.UserProfile
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import java.util.Date

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = HOST, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Authentication) {
        jwt ("auth-jwt") {
            realm = "ktor sample app"
            verifier(
                JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withAudience("planmind")
                    .withIssuer("planmind-server")
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("userId")
                        .asString() != ""
                ) JWTPrincipal(credential.payload) else null
            }
        }
    }
    routing {
        get(MIND_PLAN) {
            val data = mapOf("names" to listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Helen", "Isaac", "Julia"))
            call.respond(data)
        }

        post(USER_SIGN_IN) {
            val login = call.receive<LoginRequest>()
            if (login.email == "user@example.com" && login.password == "password") {
                val token = JWT.create()
                    .withAudience("planmind")
                    .withIssuer("planmind-server")
                    .withClaim("userId", login.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 600_000)) // 10 min
                    .sign(Algorithm.HMAC256("secret"))
                call.respond(mapOf("token" to token))
            } else {
                call.respond(mapOf("error" to "Invalid credentials"))
            }
        }

        get(USER_PROFILE) {
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
