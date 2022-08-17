package com.ducpv.utils.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Created by pvduc9773 on 26/07/2022.
 */
fun Context.showToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}