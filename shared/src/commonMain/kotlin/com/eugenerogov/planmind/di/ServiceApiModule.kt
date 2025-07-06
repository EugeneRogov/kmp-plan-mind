package com.eugenerogov.planmind.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
const val DEBUG = true

fun createHttpClient(
    engine: HttpClientEngine,
    hostHolder: HostHolder
): HttpClient {
    return HttpClient(engine) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = hostHolder.host ?: ""
            }
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Connection, "Keep-Alive")
            header(HttpHeaders.Accept, "*/*")
        }

        if (DEBUG) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

interface AuthService {

}

interface TasksService {

}

class AuthServiceImpl(private val client: HttpClient) : AuthService {

}

class TasksServiceImpl(private val client: HttpClient) : TasksService {

}

val networkModule = module {

    single { HostHolder() }

    single {
        val engine = get<HttpClientEngine>()
        val hostHolder = get<HostHolder>()
        createHttpClient(engine, hostHolder)
    }

    single<AuthService> { AuthServiceImpl(get()) }
    single<TasksService> { TasksServiceImpl(get()) }
}

class HostHolder(var host: String? = null)
