package com.eugenerogov.planmind.viewmodel.debug

import com.eugenerogov.planmind.viewmodel.UiState

data class DebugUiState(
    val content: String = ""
) : UiState {
    companion object {
        fun preview() = DebugUiState(
            content = "content"
        )
    }
}