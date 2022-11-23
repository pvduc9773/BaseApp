package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ducpv.compose.core.AppColors
import com.ducpv.model.Banner
import com.google.accompanist.pager.*

/**
 * Created by pvduc9773 on 16/11/2022.
 */
@OptIn(ExperimentalPagerApi::class)
fun LazyGridScope.itemBanners(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    banners: List<Banner>,
) {
    if (banners.isEmpty()) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPager(
                count = banners.size,
                state = pagerState,
                modifier = modifier,
            ) { page ->
                BannerPager(
                    modifier = modifier,
                    banner = banners[page]
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = AppColors.white,
                inactiveColor = AppColors.white30,
                indicatorWidth = 24.dp,
                indicatorHeight = 1.dp,
                spacing = 0.dp,
                indicatorShape = RoundedCornerShape(0.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun BannerPager(
    modifier: Modifier = Modifier,
    banner: Banner
) {
    AsyncImage(
        model = banner.image,
        contentDescription = banner.name,
        modifier = modifier
    )
}
