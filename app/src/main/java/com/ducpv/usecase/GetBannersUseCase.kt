package com.ducpv.usecase

import com.ducpv.model.Banner
import com.ducpv.repository.Repository
import javax.inject.Inject

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class GetBannersUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<List<Banner>>() {
    override suspend fun execute(vararg params: Any): List<Banner> {
        return repository.getHomePage().banners
    }
}
