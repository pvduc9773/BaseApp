package com.stdio.model.paramaters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by pvduc9773 on 08/08/2022.
 */
@Parcelize
data class SignInAccountRequest(
    val email: String,
    val password: String
) : Parcelable