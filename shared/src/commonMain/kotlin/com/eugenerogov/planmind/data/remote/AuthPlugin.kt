package com.eugenerogov.planmind.data.remote

import io.ktor.client.plugins.api.createClientPlugin

fun AuthPlugin(tokenHolder: TokenHolder) = createClientPlugin("AuthPlugin", {}) {
    onRequest { request, _ ->
        val token = tokenHolder.currentToken
        if (token.isNotEmpty()) {
            request.headers.append("Authorization", "Bearer $token")
        }
    }
}
