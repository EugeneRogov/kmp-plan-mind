package com.eugenerogov.planmind.data.remote.core

class TokenHolder(initialToken: String = "") {
    var currentToken: String = initialToken
        private set

    fun updateToken(newToken: String) {
        currentToken = newToken
    }
}