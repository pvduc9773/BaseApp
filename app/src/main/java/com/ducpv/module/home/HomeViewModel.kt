package com.ducpv.module.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducpv.usecase.GetHomePageBannersUseCase
import com.ducpv.usecase.GetHomePageCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by pvduc9773 on 03/11/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomePageBannersUseCase: GetHomePageBannersUseCase,
    private val getHomePageCategoriesUseCase: GetHomePageCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState()
        )

    init {
        viewModelScope.launch {
            try {
                awaitAll(
                    async { getBanners() },
                    async { getCategories() },
                )
                mockLoading()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getBanners() {
        getHomePageBannersUseCase
            .execute()
            .collectLatest { banners ->
                _uiState.update {
                    it.copy(
                        banners = banners
                    )
                }
            }
    }

    private suspend fun getCategories() {
        getHomePageCategoriesUseCase
            .execute()
            .collectLatest { categories ->
                _uiState.update {
                    it.copy(
                        categories = categories
                    )
                }
            }
    }

    private suspend fun mockLoading() {
        while (true) {
            delay(3_000L)
            _uiState.update { it.copy(isLoading = true) }
            delay(2_000L)
            _uiState.update { it.copy(isLoading = false) }

        }
    }
}
