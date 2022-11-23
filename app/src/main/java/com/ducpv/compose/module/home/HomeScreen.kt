package com.ducpv.compose.module.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ducpv.common.ignoreHorizontalParentPadding
import com.ducpv.compose.module.home.view.*
import com.ducpv.compose.ui.NavigationRoute
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import java.util.*
import kotlinx.coroutines.delay

/**
 * Created by pvduc9773 on 03/11/2022.
 */
fun NavGraphBuilder.homeScreen() {
    composable(route = NavigationRoute.HOME) {
        HomeRoute()
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tabState by viewModel.tabState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        tabState = tabState,
        switchTab = viewModel::switchTab
    )
}

const val ratioBannerView = 375F / 174F

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    tabState: HomeTabState,
    switchTab: (Int) -> Unit
) {
    Scaffold(topBar = { TopBar() }) { innerPadding ->
        val animateScrollBannerTime = 6_000L
        val gridState = rememberLazyGridState()
        val pagerState = rememberPagerState()

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                delay(animateScrollBannerTime)
                var nextPage = page + 1
                if (nextPage >= pagerState.pageCount) nextPage = 0
                pagerState.animateScrollToPage(nextPage)
            }
        }

        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            itemBanners(
                banners = uiState.banners,
                pagerState = pagerState,
                modifier = modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(16.dp)
                    .aspectRatio(ratioBannerView),
            )
            itemCategories(
                categories = uiState.categories,
                modifier = modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(16.dp)
            )
            itemNewestProducts(
                products = uiState.newestProducts,
                modifier = modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(16.dp)
            )
            itemTopProductsAndShops(
                products = uiState.topProducts,
                shops = uiState.topShops,
                tabState = tabState,
                switchTab = switchTab,
                modifier = modifier.fillMaxWidth()
            )
            itemSuggestProducts(
                products = uiState.suggestProducts,
                modifier = modifier.fillMaxWidth()
            )
            itemLoading(
                isLoading = uiState.isLoading,
                modifier = modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(16.dp)
            )
        }
    }
}
