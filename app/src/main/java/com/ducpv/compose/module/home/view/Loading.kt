package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ducpv.R
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.core.labelSmall

/**
 * Created by pvduc9773 on 16/11/2022.
 */
fun LazyGridScope.itemLoading(
    modifier: Modifier,
    isLoading: Boolean
) {
    if (!isLoading) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        Text(
            text = stringResource(R.string.loading),
            style = labelSmall,
            color = AppColors.black80,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(16.dp)
        )
    }
}
