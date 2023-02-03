package com.ducpv.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 20/11/2022.
 */
data class Shop(
    @SerializedName("_id") val id: String,
    @SerializedName("user") val user: User
)
