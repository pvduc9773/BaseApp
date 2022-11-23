package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ducpv.R
import com.ducpv.compose.core.AppColors

/**
 * Created by pvduc9773 on 15/11/2022.
 */

@Preview
@Composable
fun previewTopBar() {
    TopBar()
}

@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = AppColors.white,
        elevation = 1.dp,
        title = { /*TODO*/ },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(id = R.drawable.ic_bidu),
                contentDescription = null,
                tint = AppColors.black,
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    tint = AppColors.black
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = AppColors.black
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    )
}
