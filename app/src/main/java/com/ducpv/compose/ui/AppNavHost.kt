package com.ducpv.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.ducpv.compose.module.detail.productDetailScreen
import com.ducpv.compose.module.home.homeScreen
import com.ducpv.compose.module.profile.profileScreen

/**
 * Created by pvduc9773 on 06/11/2022.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationRoute.HOME,
    onBackClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()
        profileScreen()
        productDetailScreen()
    }
}

object NavigationRoute {
    const val HOME = "home_route"
    const val PROFILE = "profile_route"
    const val PRODUCT_DETAIL = "product_detail"
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(NavigationRoute.HOME, navOptions)
}

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(NavigationRoute.PROFILE, navOptions)
}

fun NavController.navigateToProductDetail(navOptions: NavOptions? = null) {
    this.navigate(NavigationRoute.PRODUCT_DETAIL, navOptions)
}
