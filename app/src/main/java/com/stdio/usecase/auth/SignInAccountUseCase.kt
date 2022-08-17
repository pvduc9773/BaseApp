package com.stdio.usecase.auth

import com.stdio.model.SignInAccount
import com.stdio.model.paramaters.SignInAccountBody
import com.stdio.repository.AuthRepository
import com.stdio.repository.Result
import com.stdio.repository.preferstore.PrefsDataStoreRepository
import com.stdio.usecase.UseCase
import com.stdio.utils.extension.runCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
class SignInAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefsDataStoreRepository: PrefsDataStoreRepository
) : UseCase<Flow<Result<SignInAccount>>>() {
    override suspend fun execute(vararg params: Any): Flow<Result<SignInAccount>> = channelFlow {
        val result = runCatching {
            authRepository.signInAccount(params[0] as SignInAccountBody)
        }
        if (result is Result.Success && result.value != null) {
            prefsDataStoreRepository.setSignInAccount(result.value)
        }
        send(result)
    }
}