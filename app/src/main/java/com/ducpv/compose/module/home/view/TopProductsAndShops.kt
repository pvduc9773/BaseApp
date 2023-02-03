package com.ducpv.compose.module.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ducpv.common.ImageNetwork
import com.ducpv.common.ignoreHorizontalParentPadding
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.module.home.HomeTabState
import com.ducpv.model.Product
import com.ducpv.model.Shop
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/**
 * Created by pvduc9773 on 16/11/2022.
 */
fun LazyGridScope.itemTopProductsAndShops(
    modifier: Modifier = Modifier,
    tabState: HomeTabState,
    products: List<Product>,
    shops: List<Shop>,
    switchTab: (Int) -> Unit,
) {
    if (products.isEmpty() && shops.isEmpty()) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        Column(
            modifier = modifier.ignoreHorizontalParentPadding(16.dp)
        ) {
            Spacer(
                modifier = modifier
                    .height(2.dp)
                    .background(AppColors.f1f1f1)
            )
            TabRow(
                selectedTabIndex = tabState.currentIndex,
                modifier = modifier
            ) {
                tabState.titleIds.forEachIndexed { index, titleId ->
                    Tab(
                        title = stringResource(id = titleId),
                        selected = index == tabState.currentIndex,
                        onClick = { switchTab(index) },
                        modifier = modifier
                    )
                }
            }
            when (tabState.currentIndex) {
                0 -> {
                    TopProductsContent(
                        products = products,
                        modifier = modifier
                    )
                }
                1 -> {
                    TopShopsContent(
                        shops = shops,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopProductsContent(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    val pagerState = rememberPagerState()
    val pagerProducts = remember {
        products.chunked(6)
    }
    HorizontalPager(
        state = pagerState,
        count = pagerProducts.size,
        modifier = modifier
    ) { index ->
        PagerProducts(
            products = pagerProducts[index],
            modifier = modifier.aspectRatio(100F / 110F)
        )
    }
}

@Composable
fun PagerProducts(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
        modifier = modifier
    ) {
        products.forEach { product ->
            item(
                key = product.id,
                span = { GridItemSpan(maxLineSpan / 3) },
            ) {
                Product(product = product)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopShopsContent(
    modifier: Modifier = Modifier,
    shops: List<Shop>
) {
    HorizontalPager(
        count = shops.size,
        modifier = modifier
    ) { index ->
        Card(
            shape = RoundedCornerShape(4.dp),
            backgroundColor = AppColors.white,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ImageNetwork(
                model = shops[index].user.avatar
            )
        }
    }
}
