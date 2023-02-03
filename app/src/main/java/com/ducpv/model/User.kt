package com.ducpv.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 21/11/2022.
 */
data class User(
    @SerializedName("_id") val id: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("avatar") val avatar: String,
)
