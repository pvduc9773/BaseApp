package com.stdio.usecase.auth

import com.stdio.model.RegisterAccount
import com.stdio.model.paramaters.RegisterAccountBody
import com.stdio.repository.AuthRepository
import com.stdio.repository.Result
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.toErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class RegisterAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Flow<Result<RegisterAccount>>>() {
    override suspend fun execute(vararg params: Any): Flow<Result<RegisterAccount>> = flow {
        try {
            val res = authRepository.registerAccount(params[0] as RegisterAccountBody)
            emit(Result.Success(res.data))
        } catch (ex: Exception) {
            emit(ex.toErrorResult())
        }
    }
}