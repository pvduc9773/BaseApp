package com.ducpv.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.ducpv.core.AppColors

/**
 * Created by pvduc9773 on 05/11/2022.
 */
@Composable
fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    shouldShowFabButton: Boolean = false,
) {
    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = AppColors.white,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            val iconResource = if (selected) destination.selectedIcon else destination.unselectedIcon
            AppNavigationBarItem(
                selected = selected,
                icon = iconResource,
                badge = destination.badge,
                onClick = {
                    onNavigateToDestination(destination)
                },
            )
        }
    }
}

@Composable
fun RowScope.AppNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: Int,
    badge: Int = 0,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            IconBadged(icon, badge)
        },
        selectedContentColor = AppColors.black,
        unselectedContentColor = AppColors.black40,
        enabled = enabled,
        modifier = modifier
    )
}

@Composable
fun IconBadged(
    icon: Int,
    badge: Int
) {
    BadgedBox(
        badge = {
            if (badge > 0) {
                Badge(
                    backgroundColor = AppColors.black
                ) {
                    val badgeText = if (badge < 10) badge.toString() else "9+"
                    Text(
                        text = badgeText,
                        color = AppColors.white
                    )
                }
            }
        }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null
        )
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any { it.route?.contains(destination.name, true) ?: false } ?: false
