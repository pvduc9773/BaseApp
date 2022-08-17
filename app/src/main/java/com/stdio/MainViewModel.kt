package com.stdio

import androidx.lifecycle.asLiveData
import com.stdio.base.BaseViewModel
import com.stdio.model.paramaters.SignInAccountBody
import com.stdio.repository.Result
import com.stdio.usecase.auth.SignInAccountUseCase
import com.stdio.usecase.home.GetHomeBannerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val signInAccountUseCase: SignInAccountUseCase,
    private val getHomeBannerUseCase: GetHomeBannerUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<BaseState<String>>(BaseState.Loading)
    val uiState = _uiState.asLiveData()

    init {
        coroutineLaunch {
            _uiState.value = BaseState.Loading
            delay(2000)
            getSignInAccount()
        }
    }

    private suspend fun getSignInAccount() {
        signInAccountUseCase.execute(
            SignInAccountBody(
                email = "pvduc9773@gmail.com",
                password = "demodemo"
            )
        ).collectLatest {
            when (it) {
                is Result.Success -> {
                    _uiState.value = BaseState.Success(it.value?.accessToken)
                    delay(2000)
                    getHomeBanner()
                }
                is Result.Error -> _uiState.value = BaseState.Error(it.message)
            }
        }
    }

    private suspend fun getHomeBanner() {
        getHomeBannerUseCase.execute().collectLatest {
            _uiState.value = BaseState.Success(it.toString())
        }
    }
}