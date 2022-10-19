package com.ducpv.repository

import com.ducpv.service.Service
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface Repository {
    // TODO: implement
}

class RepositoryImpl @Inject constructor(
    private val service: Service
) : Repository {
    // TODO: implement
}
