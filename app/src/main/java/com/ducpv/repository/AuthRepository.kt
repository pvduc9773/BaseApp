package com.ducpv.repository

import com.ducpv.model.AccountSession
import com.ducpv.model.RegisterAccount
import com.ducpv.model.paramaters.RegisterAccountBody
import com.ducpv.model.paramaters.SignInAccountBody
import com.ducpv.service.AuthService
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface AuthRepository {
    suspend fun registerAccount(body: RegisterAccountBody): Response<RegisterAccount>
    suspend fun signInAccount(body: SignInAccountBody): Response<AccountSession>
}

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun registerAccount(body: RegisterAccountBody): Response<RegisterAccount> {
        return authService.registerAccount(body)
    }

    override suspend fun signInAccount(body: SignInAccountBody): Response<AccountSession> {
        return authService.signInAccount(body)
    }
}