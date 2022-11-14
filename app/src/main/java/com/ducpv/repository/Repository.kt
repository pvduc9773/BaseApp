package com.ducpv.repository

import com.ducpv.model.HomePage
import com.ducpv.service.Service
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository {
    suspend fun getHomePage(): HomePage
}

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository {
    override suspend fun getHomePage(): HomePage {
        return service.getHomePage().data
    }
}
