package com.ducpv.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ducpv.compose.core.AppColors

/**
 * Created by pvduc9773 on 05/11/2022.
 */
@Preview("AppContent")
@Composable
fun AppPreview() {
    AppContent()
}

@Composable
fun AppContent(
    appState: AppState = rememberAppState()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = AppColors.transparent,
        scaffoldState = appState.scaffoldState,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                AppBottomBar(
                    destinations = appState.topLevelDestinations,
                    currentDestination = appState.currentDestination,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    shouldShowFabButton = appState.shouldShowFabButton
                )
            }
        }
    ) { innerPadding ->
        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = appState.navController,
            onBackClick = appState::onBackClick
        )
    }
}
