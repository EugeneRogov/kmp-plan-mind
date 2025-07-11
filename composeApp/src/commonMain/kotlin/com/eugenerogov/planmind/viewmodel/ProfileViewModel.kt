package com.eugenerogov.planmind.viewmodel

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.eugenerogov.planmind.Failure
import com.eugenerogov.planmind.domain.AuthRepository
import com.eugenerogov.planmind.domain.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ProfileViewModel {
    val state: Value<ProfileUiState>

    fun updateFirstName(firstName: String)
    fun updateLastName(lastName: String)
    fun updateEmail(email: String)
    fun updateAvatar(avatar: String?)
    fun toggleEditing()
    fun saveProfile()
    fun loadProfile()
    fun checkAuthStatus()
    fun goToAuth()
    fun signInWithGoogle()
    fun signInWithVK()
}

class ProfileViewModelImpl(
    componentContext: ComponentContext,
    private val onProfileSaved: () -> Unit = {},
    private val onNavigateToAuth: () -> Unit = {}
) : ProfileViewModel, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ProfileUiState())
    override val state: Value<ProfileUiState> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val profileRepository: ProfileRepository by inject()
    private val authRepository: AuthRepository by inject()

    override fun updateFirstName(firstName: String) {
        _state.update { it.copy(firstName = firstName) }
    }

    override fun updateLastName(lastName: String) {
        _state.update { it.copy(lastName = lastName) }
    }

    override fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun updateAvatar(avatar: String?) {
        _state.update { it.copy(avatar = avatar) }
    }

    override fun toggleEditing() {
        _state.update { it.copy(isEditing = !it.isEditing) }
    }

    override fun checkAuthStatus() {
        scope.launch {
            val isAuthenticated = authRepository.isAuthenticated()
            _state.update { it.copy(isAuthenticated = isAuthenticated) }

            if (isAuthenticated) {
                loadProfile()
            }
        }
    }

    override fun goToAuth() {
        onNavigateToAuth()
    }

    override fun saveProfile() {
        val currentState = _state.value
        if (currentState.firstName.isBlank() || currentState.lastName.isBlank()) {
            _state.update { it.copy(errorMessage = "Please fill in all required fields") }
            return
        }

        _state.update { it.copy(isSaving = true, errorMessage = "") }

        scope.launch {
            try {
                // Simulate save process
                delay(1000)

                // Here you would typically call your API to save the profile
                _state.update {
                    it.copy(
                        isSaving = false,
                        isEditing = false
                    )
                }
                onProfileSaved()
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isSaving = false,
                        errorMessage = "Failed to save profile: ${e.message}"
                    )
                }
            }
        }
    }

    override fun loadProfile() {
        _state.update { it.copy(isLoading = true, errorMessage = "") }

        scope.launch {
            val result = profileRepository.getUserProfile()

            result.fold(
                { failure ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = when (failure) {
                                is Failure.ServerError -> failure.message
                                is Failure.NetworkError -> failure.message
                                else -> "Failed to load profile"
                            }
                        )
                    }
                },
                { profile ->
                    val nameParts = profile.name.split(" ")
                    val first = nameParts.getOrNull(0) ?: ""
                    val last = nameParts.drop(1).joinToString(" ")

                    _state.update {
                        it.copy(
                            isLoading = false,
                            firstName = first,
                            lastName = last,
                            email = profile.email,
                            avatar = profile.avatar
                        )
                    }
                }
            )
        }
    }

    override fun signInWithGoogle() {
        // TO DO: Implement Google Sign-In functionality here
    }

    override fun signInWithVK() {
        // TO DO: Implement VK ID Sign-In functionality here
    }
}