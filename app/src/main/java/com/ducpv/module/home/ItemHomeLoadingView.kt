package com.ducpv.module.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ducpv.core.AppColors
import com.ducpv.core.labelMedium

/**
 * Created by pvduc9773 on 16/11/2022.
 */
fun LazyGridScope.itemHomeLoading(
    modifier: Modifier
) {
    item(
        span = { GridItemSpan(2) },
        content = {
            Text(
                text = "Loading...",
                style = labelMedium,
                color = AppColors.black80,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(24.dp)
            )
        }
    )
}
