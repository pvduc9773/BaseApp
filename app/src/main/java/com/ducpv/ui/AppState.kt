package com.ducpv.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ducpv.R
import kotlinx.coroutines.CoroutineScope

/**
 * Created by pvduc9773 on 05/11/2022.
 */
@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState {
    return remember(scaffoldState, navController, coroutineScope) {
        AppState(scaffoldState, navController, coroutineScope)
    }
}

@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    private val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            NavigationRoute.HOME -> TopLevelDestination.HOME
            NavigationRoute.PROFILE -> TopLevelDestination.PROFILE
            else -> null
        }

    val shouldShowFabButton: Boolean
        @Composable get() = currentTopLevelDestination == TopLevelDestination.PROFILE

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val badge: Int = 0
) {
    HOME(
        selectedIcon = R.drawable.ic_home_active,
        unselectedIcon = R.drawable.ic_home_inactive,
    ),
    PROFILE(
        selectedIcon = R.drawable.ic_profile_active,
        unselectedIcon = R.drawable.ic_profile_inactive,
        badge = 10 // TODO implement
    )
}
