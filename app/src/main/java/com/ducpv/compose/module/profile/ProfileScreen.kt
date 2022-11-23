package com.ducpv.compose.module.profile

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ducpv.module.facedetect.FaceDetectActivity
import com.ducpv.compose.ui.NavigationRoute

/**
 * Created by pvduc9773 on 03/11/2022.
 */
fun NavGraphBuilder.profileScreen() {
    composable(route = NavigationRoute.PROFILE) {
        ProfileRoute()
    }
}

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    Text(
        text = "ProfileScreen",
        modifier = Modifier.clickable {
            context.startActivity(Intent(context, FaceDetectActivity::class.java))
        }
    )
}
