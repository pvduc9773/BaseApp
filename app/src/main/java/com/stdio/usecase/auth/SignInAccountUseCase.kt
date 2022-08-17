package com.stdio.usecase.auth

import com.stdio.model.SignInAccount
import com.stdio.model.paramaters.SignInAccountRequest
import com.stdio.repository.AuthRepository
import com.stdio.repository.Result
import com.stdio.repository.preferstore.PrefsDataStoreRepository
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.runCatching
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class SignInAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefsDataStoreRepository: PrefsDataStoreRepository
) : UseCase<Result<SignInAccount>>() {
    override suspend fun execute(vararg params: Any): Result<SignInAccount> {
        val res = runCatching {
            authRepository.signInAccount(params[0] as SignInAccountRequest)
        }
        if (res is Result.Success) {
            prefsDataStoreRepository.setSignInAccount(res.value)
        }
        return res
    }
}