package com.ducpv.common

import android.content.Context
import androidx.annotation.StringRes
import com.ducpv.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by pvduc9773 on 26/10/2022.
 */
class CommonDialog private constructor() {
    companion object {
        fun materialBuilder(context: Context) =
            MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
    }
}

fun MaterialAlertDialogBuilder.setPositiveButton(
    @StringRes resId: Int,
    onListener: () -> Unit = {}
): MaterialAlertDialogBuilder {
    setPositiveButton(resId) { _, _ ->
        onListener()
    }
    return this
}

fun MaterialAlertDialogBuilder.setNegativeButton(
    @StringRes resId: Int,
    onListener: () -> Unit = {}
): MaterialAlertDialogBuilder {
    setNegativeButton(resId) { _, _ ->
        onListener()
    }
    return this
}
