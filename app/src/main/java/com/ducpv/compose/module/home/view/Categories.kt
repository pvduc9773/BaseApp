package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.core.bodyXSmall
import com.ducpv.model.Category

/**
 * Created by pvduc9773 on 16/11/2022.
 */
fun LazyGridScope.itemCategories(
    modifier: Modifier,
    categories: List<Category>,
) {
    if (categories.isEmpty()) return
    item(span = { GridItemSpan(maxLineSpan) }) {
        ListCategory(
            categories = categories,
            modifier = modifier,
        )
    }
}

@Composable
fun ListCategory(
    modifier: Modifier,
    categories: List<Category>,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 4.dp),
        modifier = modifier
    ) {
        categories.forEach { category ->
            item(key = category.id) {
                Category(
                    category = category,
                    modifier = modifier.width(64.dp)
                )
            }
        }
    }
}

@Composable
fun Category(
    modifier: Modifier,
    category: Category,
) {
    Column {
        AsyncImage(
            model = category.avatar,
            contentDescription = category.name,
            modifier = modifier
                .size(32.dp)
                .padding(4.dp),
        )
        Text(
            text = category.name,
            style = bodyXSmall,
            color = AppColors.black,
            textAlign = TextAlign.Center,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.fillMaxWidth()
        )
    }
}
