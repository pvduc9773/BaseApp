package com.ducpv.usecase

import com.ducpv.model.Banner
import com.ducpv.repository.Repository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class GetHomePageBannersUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<Flow<List<Banner>>>() {
    override suspend fun execute(vararg params: Any): Flow<List<Banner>> = flow {
        emit(repository.getHomePage().banners)
    }
}
