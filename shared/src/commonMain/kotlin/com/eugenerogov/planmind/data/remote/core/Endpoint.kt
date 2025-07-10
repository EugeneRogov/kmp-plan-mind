package com.eugenerogov.planmind.data.remote.core

object Endpoint {
    const val SERVER_PORT = 8080
    const val SERVER_HOST = "0.0.0.0"

    const val DEBUG_EMAIL = "1"
    const val DEBUG_PASSWORD = "2"
    val CLIENT_HOST = DebugHost().host

    private const val API_BASE = "/api"
    private const val API_V1 = "$API_BASE/v1"

    val BASE_URL = "$CLIENT_HOST:$SERVER_PORT"

    // region --- Authentication ---
    private const val AUTH_BASE = "$API_V1/auth"
    const val AUTH_SIGN_IN = "$AUTH_BASE/sign-in"
    const val AUTH_REGISTER = "$AUTH_BASE/register"
    const val AUTH_REFRESH_TOKEN = "$AUTH_BASE/refresh-token"
    const val AUTH_LOGOUT = "$AUTH_BASE/logout"
    // endregion

    // region --- Users ---
    private const val USERS_BASE = "$API_V1/users"
    const val USER_ME_PROFILE = "$USERS_BASE/me/profile"
    const val USER_PROFILE_BY_ID = "$USERS_BASE/{userId}/profile"
    // endregion

    // region --- Plan mind ---
    private const val PLAN_MIND_BASE = "$API_V1/plan-mind"
    const val MIND_PLAN_START = "$PLAN_MIND_BASE/plan-mind/start"
    const val MIND_PLAN_LIST = "$PLAN_MIND_BASE/plan-mind/list"
    // endregion

}