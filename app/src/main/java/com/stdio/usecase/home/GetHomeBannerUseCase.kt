package com.stdio.usecase.home

import com.stdio.model.Banner
import com.stdio.repository.HomeRepository
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.runCatchingToValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

/**
 * Created by pvduc9773 on 09/08/2022.
 */
class GetHomeBannerUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : UseCase<Flow<List<Banner>>>() {
    override suspend fun execute(vararg params: Any): Flow<List<Banner>> = channelFlow {
        val banners = runCatchingToValue {
            homeRepository.getHomeBanner()
        }?.result.orEmpty()
        send(banners)
    }
}