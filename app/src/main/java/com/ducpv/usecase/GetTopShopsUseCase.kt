package com.ducpv.usecase

import com.ducpv.model.Shop
import com.ducpv.repository.Repository
import javax.inject.Inject

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class GetTopShopsUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<List<Shop>>() {
    override suspend fun execute(vararg params: Any): List<Shop> {
        return repository.getTopShops()
    }
}
