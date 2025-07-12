package com.eugenerogov.planmind.viewmodel

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.eugenerogov.planmind.domain.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

interface RegisterViewModel {
    val state: Value<RegisterUiState>

    fun updateUsername(username: String)
    fun updateEmail(email: String)
    fun updatePassword(password: String)
    fun updateConfirmPassword(confirmPassword: String)
    fun updateDebugMenuExpanded(expanded: Boolean)
    fun onClickRegister()
    fun onClickBackToLogin()
}

class RegisterViewModelImpl(
    componentContext: ComponentContext,
    private val onRegisterSuccess: () -> Unit,
    private val onNavigateToLogin: () -> Unit
) : RegisterViewModel, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(RegisterUiState())
    override val state: Value<RegisterUiState> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val authRepository: AuthRepository by inject()

    override fun updateUsername(username: String) {
        _state.update { it.copy(username = username) }
    }

    override fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun updateConfirmPassword(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    override fun updateDebugMenuExpanded(expanded: Boolean) {
        _state.update { it.copy(debugMenuExpanded = expanded) }
    }

    override fun onClickRegister() {
        val currentState = _state.value

        // Validation
        if (currentState.username.isBlank() || currentState.email.isBlank() ||
            currentState.password.isBlank() || currentState.confirmPassword.isBlank()
        ) {
            // TODO: Use string resource for error message
            _state.update { it.copy(errorMessage = "Please fill in all fields") }
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            // TODO: Use string resource for error message
            _state.update { it.copy(errorMessage = "Passwords do not match") }
            return
        }

        if (currentState.password.length < 6) {
            // TODO: Use string resource for error message
            _state.update { it.copy(errorMessage = "Password must be at least 6 characters") }
            return
        }

        _state.update { it.copy(inProgress = true, errorMessage = "") }

        scope.launch {
            try {
                val result = authRepository.register(
                    stayLogged = false,
                    username = currentState.username,
                    password = currentState.password
                )
                result.fold(
                    ifLeft = { failure ->
                        _state.update {
                            it.copy(
                                inProgress = false,
                                errorMessage = failure.toString()
                            )
                        }
                    },
                    ifRight = {
                        _state.update { it.copy(inProgress = false) }
                        onRegisterSuccess()
                    }
                )
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        inProgress = false,
                        errorMessage = "Registration failed: ${e.message}"
                    )
                }
            }
        }
    }

    override fun onClickBackToLogin() {
        onNavigateToLogin()
    }
}