package com.eugenerogov.planmind.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.eugenerogov.planmind.viewmodel.BaseViewModel
import com.eugenerogov.planmind.viewmodel.UiEvent
import kotlinx.coroutines.launch

class HomeViewModel() : BaseViewModel<HomeUiState>(HomeUiState()) {

    // region API

    // endregion

    // region update UI

    // endregion

    // region Events
    private fun goToMain() {
        viewModelScope.launch {
            uiEventFlow.emit(ScreenEvents.GoToMain)
        }
    }

    sealed class ScreenEvents : UiEvent {
        data object GoToMain : ScreenEvents()
    }
    // endregion
}