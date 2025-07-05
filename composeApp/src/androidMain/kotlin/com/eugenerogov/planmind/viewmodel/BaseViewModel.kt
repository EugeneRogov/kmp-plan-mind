package com.eugenerogov.planmind.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugenerogov.planmind.Failure
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : UiState>(initialVal: T) : ViewModel() {
    protected val uiStateFlow: MutableStateFlow<T> = MutableStateFlow(initialVal)
    val uiState: StateFlow<T>
        get() = uiStateFlow.asStateFlow()

    protected val uiEventFlow = MutableSharedFlow<UiEvent>(extraBufferCapacity = 5)
    val uiEvent: SharedFlow<UiEvent> = uiEventFlow

    protected fun doFail(failure: Failure) {
        when (failure) {
            is Failure.NetworkConnection -> doFail("No connection")
            is Failure.UnknownHostException -> doShowUnknownHostException()
            is Failure.SocketTimeoutException -> doSocketTimeoutException()
            is Failure.ConnectException -> doConnectException()
            is Failure.ServerError -> doFail(failure.message)
            is Failure.Unauthorized -> doUnauthorized()
            is Failure.InvalidGrant -> doDoInvalidGrant()
            else -> doFail("Unknown error")
        }
    }

    fun inDevelop() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.InDevelop)
        }
    }

    protected fun doFail(message: String) {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.ShowError(message))
        }
    }

    fun doBack() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.DoBack)
        }
    }

    private fun doUnauthorized() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.DoUnauthorized)
        }
    }

    private fun doDoInvalidGrant() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.DoInvalidGrant)
        }
    }

    private fun doShowUnknownHostException() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.ShowUnknownHostException)
        }
    }

    private fun doSocketTimeoutException() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.ShowSocketTimeoutException)
        }
    }

    private fun doConnectException() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.ShowConnectException)
        }
    }

    fun navigateToTask(taskId: String, taskNumber: String) {
        viewModelScope.launch {
            uiEventFlow.emit(
                ScreenEvents.NavigateToTask(
                    taskId = taskId,
                    taskNumber = taskNumber
                )
            )
        }
    }

    fun navigateToCodeScanner() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.NavigateToCodeScanner)
        }
    }

    sealed class ScreenEvents : UiEvent {
        data object InDevelop : ScreenEvents()
        data class ShowError(val message: String) : ScreenEvents()
        data object ShowUnknownHostException : ScreenEvents()
        data object ShowSocketTimeoutException : ScreenEvents()
        data object ShowConnectException : ScreenEvents()
        data object DoBack : ScreenEvents()
        data object DoUnauthorized : ScreenEvents()
        data object DoInvalidGrant : ScreenEvents()
        data class NavigateToTask(val taskId: String, val taskNumber: String) : ScreenEvents()
        data object NavigateToCodeScanner : ScreenEvents()
    }
}

interface UiState

interface UiEvent
