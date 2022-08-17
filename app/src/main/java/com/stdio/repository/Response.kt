package com.stdio.repository

/**
 * Created by pvduc9773 on 08/08/2022.
 */
data class Response<T>(
    val code: Int,
    val data: T? = null,
    val message: String? = null
) {
    val isSuccessful: Boolean
        get() = code in 200..299
}