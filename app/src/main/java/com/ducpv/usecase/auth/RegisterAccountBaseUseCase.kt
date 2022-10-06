package com.ducpv.usecase.auth

import com.ducpv.model.RegisterAccount
import com.ducpv.model.paramaters.RegisterAccountBody
import com.ducpv.repository.AuthRepository
import com.ducpv.repository.Result
import com.ducpv.usecase.BaseUseCase
import com.ducpv.utils.extension.executeWithCatchingThrows
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class RegisterAccountBaseUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Result<RegisterAccount>>() {
    override suspend fun execute(vararg params: Any): Result<RegisterAccount> {
        return executeWithCatchingThrows {
            authRepository.registerAccount(params[0] as RegisterAccountBody)
        }
    }
}
