package com.ducpv

import android.view.View
import androidx.lifecycle.asLiveData
import com.ducpv.base.BaseViewModel
import com.ducpv.model.paramaters.SignInAccountBody
import com.ducpv.usecase.auth.SignInAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
sealed class MainUiState(val isLoading: Boolean) {
    object Loading : MainUiState(isLoading = true)
    class Success(val data: String?) : MainUiState(isLoading = false)
    class Error(val message: String?) : MainUiState(isLoading = false)

    val isVisibleLoading: Int get() = if (this is Loading) View.VISIBLE else View.GONE
    val isVisibleMessage: Int get() = if (this !is Loading) View.VISIBLE else View.GONE
    val messageContent: String?
        get() = when (this) {
            is Success -> data
            is Error -> message
            else -> null
        }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signInAccountUseCase: SignInAccountUseCase,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        coroutineLaunch {
            signInAccount()
        }
    }

    private suspend fun signInAccount() {
        val signInAccountBody = SignInAccountBody(
            email = "pvduc9773@gmail.com",
            password = "ASDqwe123@"
        )
        signInAccountUseCase.execute(signInAccountBody)
            .subscribe(
                onSuccess = {
                    _uiState.value = MainUiState.Success(it?.accessToken)
                },
                onFailed = {
                    _uiState.value = MainUiState.Error(it)
                }
            )
    }
}
