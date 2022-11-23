package com.ducpv.repository

import com.ducpv.model.HomePage
import com.ducpv.model.Product
import com.ducpv.model.Shop
import com.ducpv.service.Service
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository {
    suspend fun getHomePage(): HomePage
    suspend fun getNewestProducts(): List<Product>
    suspend fun getTopProducts(): List<Product>
    suspend fun getTopShops(): List<Shop>
    suspend fun getSuggestProducts(page: Int, limit: Int): List<Product>
}

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository {
    override suspend fun getHomePage(): HomePage {
        return service.getHomePage().data
    }

    override suspend fun getNewestProducts(): List<Product> {
        return service.getNewestProducts().data
    }

    override suspend fun getTopProducts(): List<Product> {
        return service.getTopProducts().data
    }

    override suspend fun getTopShops(): List<Shop> {
        return service.getTopShops().data
    }

    override suspend fun getSuggestProducts(page: Int, limit: Int): List<Product> {
        return service.getSuggestProducts(page, limit).data
    }
}
