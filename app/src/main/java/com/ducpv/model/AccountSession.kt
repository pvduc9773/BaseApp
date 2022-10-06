package com.ducpv.model

/**
 * Created by pvduc9773 on 08/08/2022.
 */
data class AccountSession(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val userId: String,
    val expired: Long,
)
