package com.ducpv.compose.module.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ducpv.compose.ui.NavigationRoute

/**
 * Created by pvduc9773 on 24/11/2022.
 */
fun NavGraphBuilder.productDetailScreen() {
    composable(route = NavigationRoute.PRODUCT_DETAIL) {
        ProductDetailRoute()
    }
}

@Composable
fun ProductDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    ProductDetailScreen()
}

@Composable
fun ProductDetailScreen() {
    Text(
        text = "ProductDetail"
    )
}
