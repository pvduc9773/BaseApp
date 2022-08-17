package com.ducpv.usecase.home

import com.ducpv.model.Banner
import com.ducpv.repository.HomeRepository
import com.ducpv.repository.Result
import com.ducpv.usecase.UseCase
import com.ducpv.utils.extension.executeWithCatchingThrows
import javax.inject.Inject

/**
 * Created by pvduc9773 on 09/08/2022.
 */
class GetHomeBannerUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : UseCase<List<Banner>>() {
    override suspend fun execute(vararg params: Any): List<Banner> {
        val res = executeWithCatchingThrows {
            homeRepository.getHomeBanner()
        }
        return when (res) {
            is Result.Success -> res.value?.result.orEmpty()
            is Result.Error -> emptyList()
        }
    }
}