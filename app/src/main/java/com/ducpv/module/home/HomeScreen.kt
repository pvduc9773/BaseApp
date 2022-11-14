package com.ducpv.module.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ducpv.ui.NavigationRoute
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

    HomeScreen(
        modifier,
        uiState
    )
}

const val ratioBannerView = 375F / 174F

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { innerPadding ->
        val animateScrollBannerTime = 12_000L
        val verticalGridState = rememberLazyGridState()
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
            state = verticalGridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (uiState.banners.isNotEmpty()) {
                itemHomeBanner(
                    banners = uiState.banners,
                    pagerState = pagerState,
                    itemHomeBannerModifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(ratioBannerView),
                )
            }

            if (uiState.categories.isNotEmpty()) {
                itemHomeCategory(
                    categories = uiState.categories,
                    modifier = modifier.fillMaxWidth(),
                )
            }

            if (uiState.isLoading) {
                itemHomeLoading(
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}
