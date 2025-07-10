package com.eugenerogov.planmind.data.remote.core

object Endpoint {
    const val SERVER_PORT = 8080
    const val SERVER_HOST = "0.0.0.0"

    const val DEBUG_EMAIL = "test@test.com"
    const val DEBUG_PASSWORD = "password"
    val CLIENT_HOST = DebugHost().host
    val BASE_URL = "$CLIENT_HOST:$SERVER_PORT"

    const val USER_SIGN_IN = "/user/sign-in"
    const val USER_REGISTER = "/user/register"
    const val USER_LOGOUT = "/user/logout"

    const val MIND_PLAN = "/plan-mind"
    const val MIND_PLAN_LIST = "/plan-mind/list"

    const val USER_PROFILE = "/user/profile"
}