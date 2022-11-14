package com.ducpv.module.home

import com.ducpv.model.Banner
import com.ducpv.model.Category

/**
 * Created by pvduc9773 on 15/11/2022.
 */
data class HomeUiState(
    val isLoading: Boolean = true,
    val banners: List<Banner> = emptyList(),
    val categories: List<Category> = emptyList()
)
