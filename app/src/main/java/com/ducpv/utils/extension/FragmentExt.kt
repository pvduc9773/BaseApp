package com.ducpv.utils.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import timber.log.Timber

/**
 * Created by pvduc9773 on 28/10/2022.
 */
fun Fragment.setDisplayHomeAsUpEnabled(displayHomeAsUp: Boolean) {
    val actionBar = (activity as? AppCompatActivity)?.supportActionBar ?: run {
        Timber.w("Activity does not have supportActionBar")
        return
    }
    actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUp)
}
