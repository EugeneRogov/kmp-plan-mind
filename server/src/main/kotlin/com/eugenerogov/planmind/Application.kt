package com.eugenerogov.planmind

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        get("/plan-mind") {
            val data = mapOf("names" to listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Helen", "Isaac", "Julia"))
            call.respond(data)
        }
    }
}

