package com.ducpv.compose.module.home.view

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ducpv.R
import com.ducpv.common.ignoreHorizontalParentPadding
import com.ducpv.model.Product

/**
 * Created by pvduc9773 on 18/11/2022.
 */
fun LazyGridScope.itemSuggestProducts(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    if (products.isEmpty()) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        Header(
            title = stringResource(R.string.suggest_for_you),
            modifier = modifier.ignoreHorizontalParentPadding(16.dp)
        )
    }
    products.forEach { product ->
        item(
            key = product.id,
            span = { GridItemSpan(maxLineSpan / 2) }
        ) {
            Product(
                product = product,
                modifier = modifier
            )
        }
    }
}
