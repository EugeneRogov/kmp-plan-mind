package com.eugenerogov.planmind.data.remote.core

import com.eugenerogov.planmind.data.remote.core.TokenHolder
import io.ktor.client.plugins.api.createClientPlugin

fun AuthPlugin(tokenHolder: TokenHolder) = createClientPlugin("AuthPlugin", {}) {
    onRequest { request, _ ->
        val token = tokenHolder.currentToken
        if (token.isNotEmpty()) {
            request.headers.append("Authorization", "Bearer $token")
        }
    }
}
