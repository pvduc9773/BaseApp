package com.ducpv.usecase

import com.ducpv.model.Product
import com.ducpv.model.Shop
import com.ducpv.extension.combine
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Created by pvduc9773 on 20/11/2022.
 */
data class TopProductsAndShopsWrapper(
    val topProducts: List<Product>,
    val topShops: List<Shop>
)

class GetTopProductsAndShopsUseCase @Inject constructor(
    private val getTopProductsUseCase: GetTopProductsUseCase,
    private val getTopShopsUseCase: GetTopShopsUseCase
) : UseCase<TopProductsAndShopsWrapper>() {
    override suspend fun execute(vararg params: Any): TopProductsAndShopsWrapper {
        return coroutineScope {
            combine(
                async { getTopProductsUseCase.execute() },
                async { getTopShopsUseCase.execute() }
            ) { topProducts, topShops ->
                return@coroutineScope TopProductsAndShopsWrapper(topProducts, topShops)
            }
        }
    }
}
