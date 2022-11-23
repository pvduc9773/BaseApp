package com.ducpv.common

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.ducpv.R

/**
 * Created by pvduc9773 on 18/11/2022.
 */
fun Modifier.ignoreHorizontalParentPadding(horizontal: Dp): Modifier {
    return this.layout { measurable, constraints ->
        var overrideWidth = constraints.maxWidth + 2 * horizontal.roundToPx()
        if (overrideWidth < 0) overrideWidth = constraints.maxWidth
        val placeable = measurable.measure(constraints.copy(maxWidth = overrideWidth))
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

@Composable
fun ImageDrawable(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = resId),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = modifier
    )
}

@Composable
fun ImageNetwork(
    model: String?,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = contentScale,
        placeholder = painterResource(id = R.drawable.ic_bidu),
        error = painterResource(id = R.drawable.ic_bidu),
        modifier = modifier
    )
}
