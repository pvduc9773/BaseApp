package com.stdio.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stdio.repository.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseViewModel : ViewModel() {
    sealed class BaseState<out T> {
        object Loading : BaseState<Nothing>()
        class Success<out T>(val value: T?) : BaseState<T>()
        class Error(val message: String?) : BaseState<Nothing>()
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Timber.e("coroutineException: $e")
    }

    protected fun coroutineLaunch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            block()
        }
    }

    suspend fun <T> Flow<Result<T>>.subscribe(
        onSuccess: suspend (T?) -> Unit,
        onError: suspend (String?) -> Unit
    ) {
        this.collectLatest {
            when (it) {
                is Result.Success -> onSuccess.invoke(it.value)
                is Result.Error -> onError.invoke(it.message)
            }
        }
    }

    suspend fun <T> Result<T>.subscribe(
        onSuccess: suspend (T?) -> Unit,
        onError: suspend (String?) -> Unit
    ) {
        when (this) {
            is Result.Success -> onSuccess.invoke(value)
            is Result.Error -> onError.invoke(message)
        }
    }
}