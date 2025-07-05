package com.eugenerogov.planmind

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform