package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.core.titleNormal

/**
 * Created by pvduc9773 on 21/11/2022.
 */
@Composable
fun TabRow(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        backgroundColor = AppColors.white,
        contentColor = AppColors.black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = AppColors.black,
            )
        },
        edgePadding = 0.dp,
        divider = {
            Divider(
                modifier = Modifier,
                thickness = 2.dp, color = AppColors.white
            )
        },
        tabs = tabs
    )
}

@Composable
fun Tab(
    modifier: Modifier = Modifier,
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Tab(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        text = {
            ProvideTextStyle(
                value = titleNormal.copy(textAlign = TextAlign.Center),
                content = {
                    Box(modifier = modifier.padding(4.dp)) {
                        Text(text = title)
                    }
                }
            )
        },
    )
}
