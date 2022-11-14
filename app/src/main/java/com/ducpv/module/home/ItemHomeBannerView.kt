package com.ducpv.module.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ducpv.core.AppColors
import com.ducpv.model.Banner
import com.google.accompanist.pager.*

/**
 * Created by pvduc9773 on 16/11/2022.
 */
@OptIn(ExperimentalPagerApi::class)
fun LazyGridScope.itemHomeBanner(
    pagerState: PagerState,
    banners: List<Banner>,
    itemHomeBannerModifier: Modifier,
) {
    item(
        span = { GridItemSpan(2) },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                HorizontalPager(
                    count = banners.size,
                    state = pagerState,
                    modifier = itemHomeBannerModifier.fillMaxSize(),
                ) { page ->
                    ItemHomeBannerPage(
                        modifier = itemHomeBannerModifier,
                        banner = banners[page]
                    )
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = AppColors.white,
                    inactiveColor = AppColors.white30,
                    indicatorWidth = 16.dp,
                    indicatorHeight = 1.dp,
                    spacing = 0.dp,
                    indicatorShape = RoundedCornerShape(0.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    )
}

@Composable
fun ItemHomeBannerPage(
    modifier: Modifier,
    banner: Banner
) {
    AsyncImage(
        model = banner.image,
        contentDescription = banner.name,
        modifier = modifier.fillMaxSize()
    )
}

