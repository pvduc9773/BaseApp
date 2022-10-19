package com.ducpv

import androidx.lifecycle.asLiveData
import com.ducpv.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
sealed class MainUiState {
    object Loading : MainUiState()
}

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        onLaunchCoroutine {
            // TODO: implement
        }
    }
}
