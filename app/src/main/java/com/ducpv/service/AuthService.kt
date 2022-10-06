package com.ducpv.service

import com.ducpv.model.AccountSession
import com.ducpv.model.RegisterAccount
import com.ducpv.model.paramaters.RegisterAccountBody
import com.ducpv.model.paramaters.SignInAccountBody
import com.ducpv.repository.Response
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
    ): Response<AccountSession>
}
