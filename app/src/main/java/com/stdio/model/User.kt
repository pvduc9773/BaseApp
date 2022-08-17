package com.stdio.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by pvduc9773 on 26/07/2022.
 */
@Parcelize
data class User(
    val name: String
) : Parcelable