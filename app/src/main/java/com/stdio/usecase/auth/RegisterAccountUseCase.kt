package com.stdio.usecase.auth

import com.stdio.model.RegisterAccount
import com.stdio.model.paramaters.RegisterAccountRequest
import com.stdio.repository.AuthRepository
import com.stdio.repository.Result
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.runCatching
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class RegisterAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Result<RegisterAccount>>() {
    override suspend fun execute(vararg params: Any): Result<RegisterAccount> {
        return runCatching {
            authRepository.registerAccount(params[0] as RegisterAccountRequest)
        }
    }
}