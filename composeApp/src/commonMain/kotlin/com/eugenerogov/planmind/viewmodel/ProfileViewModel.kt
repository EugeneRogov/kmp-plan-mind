package com.eugenerogov.planmind.viewmodel

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.eugenerogov.planmind.domain.ProfileRepository
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
}

class ProfileViewModelImpl(
    componentContext: ComponentContext,
    private val onProfileSaved: () -> Unit = {}
) : ProfileViewModel, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ProfileUiState())
    override val state: Value<ProfileUiState> = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val profileRepository: ProfileRepository by inject()

    init {
        loadProfile()
    }

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
            try {
                // Simulate loading process
                delay(500)

                // Here you would typically call your API to load the profile
                // For now, we'll just simulate loading mock data
                _state.update {
                    it.copy(
                        isLoading = false,
                        firstName = "John",
                        lastName = "Doe",
                        email = "john.doe@example.com",
                        avatar = "https://example.com/avatar.jpg"
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to load profile: ${e.message}"
                    )
                }
            }
        }
    }
}