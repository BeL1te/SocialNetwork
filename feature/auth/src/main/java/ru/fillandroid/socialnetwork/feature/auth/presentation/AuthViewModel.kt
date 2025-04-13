package ru.fillandroid.socialnetwork.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.fillandroid.socialnetwork.common.extensions.launchIO
import ru.fillandroid.socialnetwork.domain.use_case.auth.RequestCodeUseCase
import ru.fillandroid.socialnetwork.domain.use_case.auth.ValidateCodeUseCase

class AuthViewModel(
    private val requestCodeUseCase: RequestCodeUseCase,
    private val validateCodeUseCase: ValidateCodeUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun requestCode() {
        viewModelScope.launchIO(
            onAction = {
                requestCodeUseCase()
                _eventFlow.emit(UiEvent.ShowCodeInput)
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    fun validateCode(code: String) {
        viewModelScope.launchIO(
            onAction = {
                validateCodeUseCase(code)
                _eventFlow.emit(UiEvent.NavigateFeed)
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
        data object ShowCodeInput : UiEvent()
        data object NavigateFeed : UiEvent()
    }
}