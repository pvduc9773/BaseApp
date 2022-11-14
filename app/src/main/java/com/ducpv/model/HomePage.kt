package com.ducpv.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 15/11/2022.
 */
data class HomePage(
    @SerializedName("system_banner") val banners: List<Banner>,
    @SerializedName("system_category") val categories: List<Category>
)

data class Banner(
    val name: String,
    val image: String
)

data class Category(
    val name: String,
    val avatar: String
)
