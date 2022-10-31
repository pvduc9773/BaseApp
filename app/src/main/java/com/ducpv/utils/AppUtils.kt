package com.ducpv.utils

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.navigation.NavOptions
import com.ducpv.BuildConfig
import com.ducpv.R

/**
 * Created by pvduc9773 on 26/07/2022.
 */
val defaultNavOptions: NavOptions
    get() {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_from_right)
            .setExitAnim(R.anim.slide_to_left)
            .setPopEnterAnim(R.anim.slide_from_left)
            .setPopExitAnim(R.anim.slide_to_right)
            .build()
    }

val settingsIntent by lazy {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
    }
}
