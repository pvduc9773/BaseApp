package com.ducpv.service

import com.ducpv.model.BannerResult
import com.ducpv.repository.Response
import retrofit2.http.GET

/**
 * Created by pvduc9773 on 09/08/2022.
 */
interface HomeService {
    @GET("api/v1/user/banners")
    suspend fun getHomeBanner(): Response<BannerResult>
}