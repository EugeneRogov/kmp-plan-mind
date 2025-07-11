package com.eugenerogov.planmind.routes

import com.auth0.jwt.exceptions.JWTVerificationException
import com.eugenerogov.planmind.JwtService
import com.eugenerogov.planmind.data.remote.core.Endpoint.AUTH_REFRESH_TOKEN
import com.eugenerogov.planmind.data.remote.core.Endpoint.AUTH_REGISTER
import com.eugenerogov.planmind.data.remote.core.Endpoint.AUTH_SIGN_IN
import com.eugenerogov.planmind.data.remote.core.Endpoint.AUTH_LOGOUT
import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_EMAIL
import com.eugenerogov.planmind.data.remote.core.Endpoint.DEBUG_PASSWORD
import com.eugenerogov.planmind.domain.entities.auth.LoginRequest
import com.eugenerogov.planmind.domain.entities.auth.LoginResponse
import com.eugenerogov.planmind.domain.entities.auth.RefreshRequest
import com.eugenerogov.planmind.domain.entities.auth.RegisterRequest
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

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

    post(AUTH_REGISTER) {
        val register = call.receive<RegisterRequest>()
        if (register.email == DEBUG_EMAIL) {
            call.respond(mapOf("error" to "User already exists"))
        } else {
            val token = JwtService().generateToken(register.email)
            call.respond(LoginResponse(token = token))
        }
    }

    post(AUTH_REFRESH_TOKEN) {
        val refresh = call.receive<RefreshRequest>()
        try {
            val decoded = JwtService().getJwtVerifier().verify(refresh.token)
            val userId = decoded.getClaim("userId").asString()
            val newToken = JwtService().generateToken(userId)
            call.respond(LoginResponse(token = newToken))
        } catch (e: JWTVerificationException) {
            call.respond(mapOf("error" to "Invalid or expired token"))
        } catch (e: Exception) {
            call.respond(mapOf("error" to (e.message ?: "Unknown error")))
        }
    }
}
