package com.ducpv.usecase

import com.ducpv.model.Product
import com.ducpv.repository.Repository
import javax.inject.Inject

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class GetSuggestProductsUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<List<Product>>() {
    override suspend fun execute(vararg params: Any): List<Product> {
        val page = params[0] as Int
        val limit = params[1] as Int
        return repository.getSuggestProducts(page, limit)
    }
}
