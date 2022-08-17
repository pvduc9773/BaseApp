package com.stdio.service

import com.stdio.model.BannerResult
import com.stdio.repository.Response
import retrofit2.http.GET

/**
 * Created by pvduc9773 on 09/08/2022.
 */
interface HomeService {
    @GET("api/v1/user/banners")
    suspend fun getHomeBanner(): Response<BannerResult>
}