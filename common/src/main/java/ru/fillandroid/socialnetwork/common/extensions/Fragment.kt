package ru.fillandroid.socialnetwork.common.extensions


import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun <ViewState> Fragment.collectUiState(
    viewState: StateFlow<ViewState>,
    crossinline updateScreenState: (viewState: ViewState) -> Unit,
) {
    lifecycleScope.launch {
        viewState.collectLatest {
            updateScreenState.invoke(it)
        }
    }
}
