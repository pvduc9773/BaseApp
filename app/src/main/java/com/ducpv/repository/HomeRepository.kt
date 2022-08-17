package com.ducpv.repository

import com.ducpv.model.BannerResult
import com.ducpv.service.HomeService
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface HomeRepository {
    suspend fun getHomeBanner(): Response<BannerResult>
}

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun getHomeBanner(): Response<BannerResult> {
        return homeService.getHomeBanner()
    }
}