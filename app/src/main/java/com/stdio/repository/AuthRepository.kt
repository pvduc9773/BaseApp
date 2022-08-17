package com.stdio.repository

import com.stdio.model.RegisterAccount
import com.stdio.model.SignInAccount
import com.stdio.model.paramaters.RegisterAccountRequest
import com.stdio.model.paramaters.SignInAccountRequest
import com.stdio.service.AuthService
import javax.inject.Inject

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface AuthRepository {
    suspend fun registerAccount(body: RegisterAccountRequest): Response<RegisterAccount>
    suspend fun signInAccount(body: SignInAccountRequest): Response<SignInAccount>
}

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun registerAccount(body: RegisterAccountRequest): Response<RegisterAccount> {
        return authService.registerAccount(body)
    }

    override suspend fun signInAccount(body: SignInAccountRequest): Response<SignInAccount> {
        return authService.signInAccount(body)
    }
}