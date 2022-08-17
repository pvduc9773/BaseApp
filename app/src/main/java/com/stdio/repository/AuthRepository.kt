package com.stdio.repository

import com.stdio.model.RegisterAccount
import com.stdio.model.SignInAccount
import com.stdio.model.paramaters.RegisterAccountBody
import com.stdio.model.paramaters.SignInAccountBody
import com.stdio.service.AuthService
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface AuthRepository {
    suspend fun registerAccount(body: RegisterAccountBody): Response<RegisterAccount>
    suspend fun signInAccount(body: SignInAccountBody): Response<SignInAccount>
}

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun registerAccount(body: RegisterAccountBody): Response<RegisterAccount> {
        return authService.registerAccount(body)
    }

    override suspend fun signInAccount(body: SignInAccountBody): Response<SignInAccount> {
        return authService.signInAccount(body)
    }
}