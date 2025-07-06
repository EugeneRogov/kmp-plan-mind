package com.eugenerogov.planmind.viewmodel.home

import com.eugenerogov.planmind.viewmodel.UiState

data class HomeUiState(
    val content: String = ""
) : UiState {
    companion object {
        fun preview() = HomeUiState(
            content = "content"
        )
    }
}