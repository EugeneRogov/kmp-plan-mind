package com.eugenerogov.planmind.data.remote

import io.ktor.client.HttpClient

interface ProfileService {

}

class ProfileServiceImpl(private val client: HttpClient) : ProfileService {

}