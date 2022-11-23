package com.ducpv.usecase

import com.ducpv.model.Category
import com.ducpv.repository.Repository
import javax.inject.Inject

/**
 * Created by pvduc9773 on 15/11/2022.
 */
class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<List<Category>>() {
    override suspend fun execute(vararg params: Any): List<Category> {
        return repository.getHomePage().categories
    }
}
