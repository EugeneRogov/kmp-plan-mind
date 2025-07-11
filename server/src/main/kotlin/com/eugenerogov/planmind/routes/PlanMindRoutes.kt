package com.eugenerogov.planmind.routes

import com.eugenerogov.planmind.data.remote.core.Endpoint.MIND_PLAN_START
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.planMindRoutes() {
    get(MIND_PLAN_START) {
        val data = mapOf(
            "names" to listOf(
                "Alice",
                "Bob",
                "Charlie",
                "David",
                "Eve",
                "Frank",
                "Grace",
                "Helen",
                "Isaac",
                "Julia"
            )
        )
        call.respond(data)
    }
}
