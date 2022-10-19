package com.ducpv.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by pvduc9773 on 25/07/2022.
 */
abstract class BaseViewModel : ViewModel() {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Timber.e("coroutineException: $e")
    }

    protected fun onLaunchCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        executeBlock: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            executeBlock()
        }
    }
}
