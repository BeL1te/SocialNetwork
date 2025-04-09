package ru.fillandroid.socialnetwork.common.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

inline fun CoroutineScope.launchIO(
    crossinline onAction: suspend () -> Unit,
    crossinline onError: suspend (Throwable) -> Unit,
    errorDispatcher: CoroutineDispatcher = Dispatchers.Main
): Job {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(errorDispatcher) {
            onError(throwable)
        }
    }

    return this.launch(exceptionHandler + Dispatchers.IO) {
        onAction()
    }
}
