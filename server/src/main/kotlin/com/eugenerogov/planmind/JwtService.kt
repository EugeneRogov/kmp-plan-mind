package com.eugenerogov.planmind

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

object AuthConfig {
    const val SECRET = "your_secure_secret_from_env" // лучше из переменных окружения
    const val AUDIENCE = "planmind"
    const val ISSUER = "planmind-server"
    const val REALM = "ktor sample app"
    const val TOKEN_EXPIRY_MS = 600_000L
}

class JwtService {
    fun generateToken(userId: String): String {
        return JWT.create()
            .withAudience(AuthConfig.AUDIENCE)
            .withIssuer(AuthConfig.ISSUER)
            .withClaim("userId", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + AuthConfig.TOKEN_EXPIRY_MS))
            .sign(Algorithm.HMAC256(AuthConfig.SECRET))
    }

    fun getJwtVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(AuthConfig.SECRET))
            .withAudience(AuthConfig.AUDIENCE)
            .withIssuer(AuthConfig.ISSUER)
            .build()
    }
}