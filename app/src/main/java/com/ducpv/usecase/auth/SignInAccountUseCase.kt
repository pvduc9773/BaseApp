package com.ducpv.usecase.auth

import com.ducpv.model.AccountSession
import com.ducpv.model.paramaters.SignInAccountBody
import com.ducpv.repository.AuthRepository
import com.ducpv.repository.Result
import com.ducpv.repository.preferstore.PrefsDataStoreRepository
import com.ducpv.repository.preferstore.getAccessToken
import com.ducpv.usecase.UseCase
import com.ducpv.utils.extension.executeResponse
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class SignInAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefsDataStoreRepository: PrefsDataStoreRepository
) : UseCase<Result<AccountSession>>() {
    override suspend fun execute(vararg params: Any): Result<AccountSession> {
        prefsDataStoreRepository.clearAccountSession()
        val result = executeResponse {
            authRepository.signInAccount(params[0] as SignInAccountBody)
        }
        if (result is Result.Success) {
            prefsDataStoreRepository.setAccountSession(result.value)
        }
        return result
    }
}
