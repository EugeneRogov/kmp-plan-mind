package com.eugenerogov.planmind.data.remote

import io.ktor.client.HttpClient

interface AuthService {

}

class AuthServiceImpl(private val client: HttpClient) : AuthService {

}