package com.eugenerogov.planmind.data.remote

object Endpoint {
    const val SERVER_PORT = 8080
    const val HOST = "0.0.0.0"
    const val BASE_URL = "http://$HOST:$SERVER_PORT"

    const val USER_SIGN_IN = "/user/sign-in"
    const val USER_REGISTER = "/user/register"
    const val USER_LOGOUT = "/user/logout"

    const val MIND_PLAN = "/plan-mind"
    const val MIND_PLAN_LIST = "/plan-mind/list"

    const val USER_PROFILE = "/user/profile"
}
