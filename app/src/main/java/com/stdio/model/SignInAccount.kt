package com.stdio.model

/**
 * Created by pvduc9773 on 08/08/2022.
 */
data class SignInAccount(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String,
    val userId: String,
    val expired: Long,
)