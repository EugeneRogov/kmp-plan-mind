package com.eugenerogov.planmind.routes

import com.eugenerogov.planmind.JwtService
import com.eugenerogov.planmind.data.remote.core.Endpoint.AUTH_SIGN_IN
import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_EMAIL
import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_PASSWORD
import com.eugenerogov.planmind.domain.entities.auth.LoginRequest
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*

fun Route.authRoutes() {
    post(AUTH_SIGN_IN) {
        val login = call.receive<LoginRequest>()
        if (login.email == DEBUG_EMAIL && login.password == DEBUG_PASSWORD) {
            val token = JwtService().generateToken(login.email)
            call.respond(LoginResponse(token = token))
        } else {
            call.respond(mapOf("error" to "Invalid credentials"))
        }
    }
}
