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
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

data class Category(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String
)
