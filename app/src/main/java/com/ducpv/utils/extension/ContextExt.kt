package com.ducpv.utils.extension

import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.getSystemService
import com.ducpv.R
import com.ducpv.common.CommonDialog
import com.ducpv.common.setNegativeButton
import com.ducpv.common.setPositiveButton

/**
 * Created by pvduc9773 on 26/07/2022.
 */
fun Context.isPermissionGranted(permission: String): Boolean =
    checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

fun Context.checkPermissions(permissions: List<String>): Boolean =
    permissions.all { isPermissionGranted(it) }

fun Context.isBluetoothEnabled(): Boolean =
    getSystemService<BluetoothManager>()?.adapter?.isEnabled ?: false

fun Context.showToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showRationaleDialog(
    @StringRes message: Int,
    @StringRes positive: Int = R.string.ok,
    @StringRes negative: Int = R.string.cancel,
    onPositiveListener: () -> Unit,
    onNegativeListener: (() -> Unit)? = null
) {
    CommonDialog.materialBuilder(this)
        .setMessage(message)
        .setPositiveButton(positive, onPositiveListener)
        .setNegativeButton(negative)
        .apply {
            if (onNegativeListener != null) {
                setNegativeButton(negative, onNegativeListener)
            }
        }
        .show()
}
