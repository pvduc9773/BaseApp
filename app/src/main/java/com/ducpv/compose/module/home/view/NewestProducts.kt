package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ducpv.R
import com.ducpv.model.Product

/**
 * Created by pvduc9773 on 16/11/2022.
 */
fun LazyGridScope.itemNewestProducts(
    modifier: Modifier,
    products: List<Product>,
) {
    if (products.isEmpty()) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        Header(
            title = stringResource(R.string.newest_products),
            modifier = modifier
        )
    }
    item(span = { GridItemSpan(maxLineSpan) }) {
        ListNewestProduct(
            products = products,
            modifier = modifier,
        )
    }
}

@Composable
fun ListNewestProduct(
    modifier: Modifier,
    products: List<Product>,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val itemProductWidth = (screenWidth - 40) / 3.2
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 16.dp),
        modifier = modifier
    ) {
        products.forEach { product ->
            item(key = product.id) {
                Product(
                    product = product,
                    modifier = modifier.width(itemProductWidth.dp)
                )
            }
        }
    }
}
