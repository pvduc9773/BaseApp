package com.ducpv.compose.module.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducpv.R
import com.ducpv.model.Banner
import com.ducpv.model.Category
import com.ducpv.model.Product
import com.ducpv.model.Shop
import com.ducpv.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by pvduc9773 on 03/11/2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBannersUseCase: GetBannersUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getNewestProductsUseCase: GetNewestProductsUseCase,
    private val getTopProductsAndShopsUseCase: GetTopProductsAndShopsUseCase,
    private val getSuggestProductsUseCase: GetSuggestProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _tabState = MutableStateFlow(
        HomeTabState(
            titleIds = listOf(R.string.top_products, R.string.top_shops),
            currentIndex = 0
        )
    )
    val tabState = _tabState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getBanners()
                getCategories()
                getNewestProducts()
                getTopProductsAndShops()
                getSuggestProducts()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private suspend fun getBanners() {
        try {
            val banners = getBannersUseCase.execute()
            _uiState.update { it.copy(banners = banners) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private suspend fun getCategories() {
        try {
            val categories = getCategoriesUseCase.execute()
            _uiState.update { it.copy(categories = categories) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private suspend fun getNewestProducts() {
        try {
            val newestProducts = getNewestProductsUseCase.execute()
            _uiState.update { it.copy(newestProducts = newestProducts) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private suspend fun getTopProductsAndShops() {
        try {
            val topProductsAndShops = getTopProductsAndShopsUseCase.execute()
            _uiState.update {
                it.copy(
                    topProducts = topProductsAndShops.topProducts,
                    topShops = topProductsAndShops.topShops
                )
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private suspend fun getSuggestProducts(forceUpdate: Boolean = true) {
        try {
            if (forceUpdate) _uiState.update { it.copy(currentPage = 1) }
            val suggestProducts = getSuggestProductsUseCase.execute(_uiState.value.currentPage, 20)
            _uiState.update { it.copy(suggestProducts = suggestProducts) }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != _tabState.value.currentIndex) {
            _tabState.update {
                it.copy(currentIndex = newIndex)
            }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val banners: List<Banner> = emptyList(),
    val categories: List<Category> = emptyList(),
    val newestProducts: List<Product> = emptyList(),
    val topProducts: List<Product> = emptyList(),
    val topShops: List<Shop> = emptyList(),
    val suggestProducts: List<Product> = emptyList(),
    val currentPage: Int = 0,
)

data class HomeTabState(
    val titleIds: List<Int>,
    val currentIndex: Int
)
