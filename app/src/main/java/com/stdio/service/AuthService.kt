package com.stdio.service

import com.stdio.model.RegisterAccount
import com.stdio.model.SignInAccount
import com.stdio.model.paramaters.RegisterAccountBody
import com.stdio.model.paramaters.SignInAccountBody
import com.stdio.repository.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by pvduc9773 on 26/07/2022.
 */
interface AuthService {

    @POST("api/v1/user/authentication/register")
    suspend fun registerAccount(
        @Body body: RegisterAccountBody
    ): Response<RegisterAccount>

    @POST("api/v1/user/authentication/sign-in")
    suspend fun signInAccount(
        @Body body: SignInAccountBody
    ): Response<SignInAccount>
}