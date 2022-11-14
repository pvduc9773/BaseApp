package com.ducpv.service

import com.ducpv.model.HomePage
import retrofit2.http.GET

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Service {
    @GET("api/v2/mobile/home/banner-categories-v2")
    suspend fun getHomePage(): ApiResult<HomePage>
}
