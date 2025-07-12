package com.eugenerogov.planmind

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.eugenerogov.planmind.database.RedisConfig
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
        val token = JWT.create()
            .withAudience(AuthConfig.AUDIENCE)
            .withIssuer(AuthConfig.ISSUER)
            .withClaim("userId", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + AuthConfig.TOKEN_EXPIRY_MS))
            .sign(Algorithm.HMAC256(AuthConfig.SECRET))

        // Save token в Redis
        val jedis = RedisConfig.getPool().resource
        try {
            jedis.setex("jwt:$userId", (AuthConfig.TOKEN_EXPIRY_MS / 1000).toInt().toLong(), token)
        } finally {
            jedis.close()
        }

        return token
    }

    fun getJwtVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(AuthConfig.SECRET))
            .withAudience(AuthConfig.AUDIENCE)
            .withIssuer(AuthConfig.ISSUER)
            .build()
    }

    fun isTokenValid(userId: String, token: String): Boolean {
        val jedis = RedisConfig.getPool().resource
        return try {
            val storedToken = jedis.get("jwt:$userId")
            storedToken == token
        } finally {
            jedis.close()
        }
    }

    fun revokeToken(userId: String) {
        val jedis = RedisConfig.getPool().resource
        try {
            jedis.del("jwt:$userId")
        } finally {
            jedis.close()
        }
    }
}