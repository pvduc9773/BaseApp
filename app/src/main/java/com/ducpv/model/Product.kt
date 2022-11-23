package com.ducpv.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pvduc9773 on 18/11/2022.
 */
data class Product(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("sale_price") val salePrice: Long,
    @SerializedName("before_sale_price") val beforeSalePrice: Long
) {
    val image: String? get() = images.firstOrNull()
}
