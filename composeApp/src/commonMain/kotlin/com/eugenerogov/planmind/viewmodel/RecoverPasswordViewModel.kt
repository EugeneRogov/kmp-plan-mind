package com.eugenerogov.planmind.viewmodel

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.eugenerogov.planmind.domain.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

interface RecoverPasswordViewModel {
    val state: Value<RecoverPasswordUiState>

    fun updateEmail(email: String)
    fun updateDebugMenuExpanded(expanded: Boolean)
    fun onClickRecover()
    fun onClickBackToLogin()
}

class RecoverPasswordViewModelImpl(
    componentContext: ComponentContext,
    private val onRecoverSuccess: () -> Unit,
    private val onNavigateToLogin: () -> Unit
) : RecoverPasswordViewModel, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(RecoverPasswordUiState())
    override val state: Value<RecoverPasswordUiState> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val authRepository: AuthRepository by inject()

    override fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun updateDebugMenuExpanded(expanded: Boolean) {
        _state.update { it.copy(debugMenuExpanded = expanded) }
    }

    override fun onClickRecover() {
        val currentState = _state.value

        if (currentState.email.isBlank()) {
            _state.update { it.copy(errorMessage = "Введите email для восстановления пароля") }
            return
        }

        if (!currentState.email.contains("@") || !currentState.email.contains(".")) {
            _state.update { it.copy(errorMessage = "Введите корректный email адрес") }
            return
        }

        _state.update { it.copy(inProgress = true, errorMessage = "", successMessage = "") }

        scope.launch {
            try {
                // TODO: Implement actual password recovery API call
                // For now, simulate API call with delay
                delay(2000)

                _state.update {
                    it.copy(
                        inProgress = false,
                        successMessage = "Инструкции по восстановлению пароля отправлены на ${currentState.email}"
                    )
                }

                // Navigate back after showing success message
                delay(3000)
                onRecoverSuccess()

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        inProgress = false,
                        errorMessage = "Ошибка восстановления пароля: ${e.message}"
                    )
                }
            }
        }
    }

    override fun onClickBackToLogin() {
        onNavigateToLogin()
    }
}