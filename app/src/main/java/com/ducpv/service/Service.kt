package com.ducpv.service

import com.ducpv.model.HomePage
import com.ducpv.model.Product
import com.ducpv.model.Shop
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Service {
    @GET("api/v2/mobile/home/banner-categories-v2")
    suspend fun getHomePage(): ApiResult<HomePage>

    @GET("api/v2/mobile/home/newest-product")
    suspend fun getNewestProducts(): ApiResult<List<Product>>

    @GET("api/v2/mobile/home/top-product")
    suspend fun getTopProducts(): ApiResult<List<Product>>

    @GET("api/v2/mobile/home/top-seller-revise")
    suspend fun getTopShops(): ApiResult<List<Shop>>

    @GET("api/v1/mobile/home/suggest-products")
    suspend fun getSuggestProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ApiResult<List<Product>>
}
