package com.ducpv

import androidx.lifecycle.asLiveData
import com.ducpv.base.BaseViewModel
import com.ducpv.model.paramaters.SignInAccountBody
import com.ducpv.usecase.auth.SignInAccountBaseUseCase
import com.ducpv.usecase.home.GetHomeBannerBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
data class MainUiState(
    val isLoading: Boolean = true,
    val message: String? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signInAccountUseCase: SignInAccountBaseUseCase,
    private val getHomeBannerUseCase: GetHomeBannerBaseUseCase,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asLiveData()

    init {
        coroutineLaunch {
            signInAccount()
        }
    }

    private suspend fun signInAccount() {
        val signInAccountBody = SignInAccountBody(
            email = "pvduc9773@gmail.com",
            password = "demodemo"
        )
        signInAccountUseCase.execute(signInAccountBody)
            .subscribe(
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
