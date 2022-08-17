package com.stdio.model.paramaters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by pvduc9773 on 08/08/2022.
 */
@Parcelize
data class RegisterAccountRequest(
    val email: String,
    val password: String,
    val username: String,
    val phoneNumber: String,
    val birthDay: Long,
) : Parcelable