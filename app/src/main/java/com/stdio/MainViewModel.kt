package com.stdio

import androidx.lifecycle.asLiveData
import com.stdio.base.BaseViewModel
import com.stdio.model.paramaters.SignInAccountRequest
import com.stdio.usecase.auth.SignInAccountUseCase
import com.stdio.usecase.home.GetHomeBannerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
data class MainUiState(
    val isLoading: Boolean,
    val message: String?
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signInAccountUseCase: SignInAccountUseCase,
    private val getHomeBannerUseCase: GetHomeBannerUseCase,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(
        MainUiState(
            isLoading = true,
            message = null
        )
    )
    val uiState = _uiState.asLiveData()

    init {
        coroutineLaunch {
            signInAccount()
        }
    }

    private suspend fun signInAccount() {
        val signInAccountRequest = SignInAccountRequest(
            email = "pvduc9773@gmail.com",
            password = "demodemo"
        )
        signInAccountUseCase.execute(signInAccountRequest).subscribe(
            onSuccess = {
                _uiState.update { state ->
                    state.copy(
                        isLoading = it?.accessToken != null,
                        message = it?.accessToken
                    )
                }
                delay(2000)
                getHomeBanner()
            },
            onError = {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        message = it
                    )
                }
            }
        )
    }

    private suspend fun getHomeBanner() {
        val banners = getHomeBannerUseCase.execute()
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                message = banners.toString()
            )
        }
    }
}