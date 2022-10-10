package com.ducpv.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducpv.repository.Result
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseViewModel : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Timber.e("coroutineException: $e")
    }

    protected fun coroutineLaunch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        executeBlock: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            executeBlock()
        }
    }

    suspend fun <T> Result<T>.subscribe(
        onSuccess: suspend (T?) -> Unit,
        onFailed: suspend (String?) -> Unit
    ) {
        when (this) {
            is Result.Success -> onSuccess.invoke(value)
            is Result.Error -> onFailed.invoke(message)
        }
    }
}
