package com.ducpv.common

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.ducpv.extension.loadImage
import java.io.File

/**
 * Created by pvduc9773 on 24/10/2022.
 */
@BindingAdapter("bindingSetTextStringRes")
fun TextView.bindingSetStringRes(@StringRes stringRes: Int?) {
    if (stringRes == null || stringRes == -1) return
    this.setText(stringRes)
}

@BindingAdapter("bindingSetTextString")
fun TextView.bindingSetTextString(string: String?) {
    if (string == null) return
    this.text = string
}

@BindingAdapter("bindingLoadImageUrl")
fun ImageView.bindingLoadImageUrl(url: String?) {
    this.loadImage(url)
}

@BindingAdapter("bindingLoadImageFile")
fun ImageView.bindingLoadImageFile(file: File?) {
    this.loadImage(file)
}
