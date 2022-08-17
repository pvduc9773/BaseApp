package com.stdio.usecase.home

import com.stdio.model.Banner
import com.stdio.repository.HomeRepository
import com.stdio.repository.Result
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.runCatching
import javax.inject.Inject

/**
 * Created by pvduc9773 on 09/08/2022.
 */
class GetHomeBannerUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : UseCase<List<Banner>>() {
    override suspend fun execute(vararg params: Any): List<Banner> {
        val res = runCatching {
            homeRepository.getHomeBanner()
        }
        return when (res) {
            is Result.Success -> res.value?.result.orEmpty()
            is Result.Error -> emptyList()
        }
    }
}