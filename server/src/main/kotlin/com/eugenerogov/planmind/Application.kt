package com.eugenerogov.planmind

import com.eugenerogov.planmind.data.remote.core.Endpoint.SERVER_HOST
import com.eugenerogov.planmind.data.remote.core.Endpoint.SERVER_PORT
import com.eugenerogov.planmind.routes.authRoutes
import com.eugenerogov.planmind.routes.planMindRoutes
import com.eugenerogov.planmind.routes.userRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import com.eugenerogov.planmind.database.DatabaseConfig
import com.eugenerogov.planmind.domain.entities.profile.UserProfileTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = SERVER_HOST, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // init and migration db
    DatabaseConfig.init()
    transaction {
        SchemaUtils.create(UserProfileTable)
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Authentication) {
        jwt ("auth-jwt") {
            realm = AuthConfig.REALM
            verifier(JwtService().getJwtVerifier())
            validate { credential ->
                if (credential.payload.getClaim("userId")
                        .asString() != ""
                ) JWTPrincipal(credential.payload) else null
            }
        }
    }
    routing {
        authRoutes()
        userRoutes()
        planMindRoutes()
    }
}
