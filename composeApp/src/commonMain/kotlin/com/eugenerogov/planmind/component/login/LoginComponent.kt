package com.eugenerogov.planmind.component.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.eugenerogov.planmind.component.login.LoginUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

interface LoginComponent {
    val state: Value<LoginUiState>

    fun updateLogin(login: String)
    fun updatePassword(password: String)
    fun updateLoginIn(isLoginIn: Boolean)
    fun updateDebugMenuExpanded(expanded: Boolean)
    fun onClickLogin()
    fun onClickForgotPassword()
    fun onClickNetworkSettings()
}

class DefaultLoginComponent(
    componentContext: ComponentContext,
    private val onLoginSuccess: () -> Unit,
    private val onNavigateToForgotPassword: () -> Unit,
    private val onNavigateToNetworkSettings: () -> Unit
) : LoginComponent, ComponentContext by componentContext {

    private val _state = MutableValue(LoginUiState())
    override val state: Value<LoginUiState> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun updateLogin(login: String) {
        _state.update { it.copy(login = login) }
    }

    override fun updatePassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun updateLoginIn(isLoginIn: Boolean) {
        _state.update { it.copy(isCheckLoginIn = isLoginIn) }
    }

    override fun updateDebugMenuExpanded(expanded: Boolean) {
        _state.update { it.copy(debugMenuExpanded = expanded) }
    }

    override fun onClickLogin() {
        val currentState = _state.value
        if (currentState.login.isBlank() || currentState.password.isBlank()) {
            _state.update { it.copy(errorMessage = "Please fill in all fields") }
            return
        }

        _state.update { it.copy(inProgress = true, errorMessage = "") }

        scope.launch {
            try {
                // Simulate login process
                kotlinx.coroutines.delay(1000)

                // Here you would typically call your authentication service
                // For now, we'll just simulate a successful login
                if (currentState.login == "test@test.com" && currentState.password == "password") {
                    _state.update { it.copy(inProgress = false) }
                    onLoginSuccess()
                } else {
                    _state.update {
                        it.copy(
                            inProgress = false,
                            errorMessage = "Invalid credentials"
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        inProgress = false,
                        errorMessage = "Login failed: ${e.message}"
                    )
                }
            }
        }
    }

    override fun onClickForgotPassword() {
        onNavigateToForgotPassword()
    }

    override fun onClickNetworkSettings() {
        _state.update { it.copy(isNetworkSettings = true) }
        onNavigateToNetworkSettings()
    }
}