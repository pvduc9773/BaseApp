package com.ducpv.model

/**
 * Created by pvduc9773 on 08/08/2022.
 */
data class RegisterAccount(
    val email: String,
    val password: String,
    val username: String,
    val phoneNumber: String,
    val birthDay: Long,
)
