package com.ducpv.compose.module.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ducpv.R
import com.ducpv.common.ImageDrawable
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.core.headlineMedium

/**
 * Created by pvduc9773 on 20/11/2022.
 */
@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(
            modifier = modifier
                .background(AppColors.f1f1f1)
                .height(2.dp)
                .fillMaxWidth()
        )
        Spacer(
            modifier = modifier
                .height(16.dp)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Text(text = title, style = headlineMedium)
            ImageDrawable(resId = R.drawable.ic_arrow_next)
        }
    }
}
