package com.eugenerogov.planmind.di

import com.eugenerogov.planmind.data.remote.AuthService
import com.eugenerogov.planmind.data.remote.AuthServiceImpl
import com.eugenerogov.planmind.data.remote.core.Endpoint.BASE_URL
import com.eugenerogov.planmind.data.remote.core.HostHolder
import com.eugenerogov.planmind.data.remote.ProfileService
import com.eugenerogov.planmind.data.remote.ProfileServiceImpl
import com.eugenerogov.planmind.data.remote.core.TokenHolder
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

val serviceApiModule = module {

    single { HostHolder(BASE_URL) }
    single { TokenHolder() }

    single {
        val hostHolder: HostHolder = get()
        createHttpClient(hostHolder)
    }

    single<AuthService> { AuthServiceImpl(get()) }
    single<ProfileService> { ProfileServiceImpl(get()) }
}

fun createHttpClient(
    hostHolder: HostHolder
): HttpClient {
    return HttpClient() {
        defaultRequest {
            url {
//                protocol = URLProtocol.HTTPS
                protocol = URLProtocol.HTTP
                host = hostHolder.host
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
