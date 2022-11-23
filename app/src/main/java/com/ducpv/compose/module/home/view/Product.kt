package com.ducpv.compose.module.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ducpv.common.ImageNetwork
import com.ducpv.compose.core.AppColors
import com.ducpv.compose.core.bodySmall
import com.ducpv.model.Product

/**
 * Created by pvduc9773 on 20/11/2022.
 */
@Composable
fun Product(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column {
        Card(
            shape = RoundedCornerShape(4.dp),
            backgroundColor = AppColors.white,
            modifier = modifier.aspectRatio(100F / 130F)
        ) {
            ImageNetwork(
                model = product.image
            )
        }
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = product.name,
            style = bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(4.dp))
    }
}
