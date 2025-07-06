package com.eugenerogov.planmind.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher

/**
 * SignIn with login
 *
 * @see
 */
class SignInLoginUseCase(
//    private val repository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(stayLogged: Boolean, username: String, password: String) =
        ""
//        withContext(dispatcher) {
//            val user = username.trim()
//            val pass = password.trim()
//            repository.token(
//                stayLogged = stayLogged,
//                username = user,
//                password = pass
//            )
//        }
}
