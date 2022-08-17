package com.stdio.repository

/**
 * Created by pvduc9773 on 12/08/2022.
 */
sealed class Result<out T> {
    class Success<T>(val value: T?) : Result<T>()
    class Error(val code: Int = -1, val message: String? = null) : Result<Nothing>()
}

enum class ErrorCode(val code: Int) {
    GENERIC(511),
    NETWORK(512),
    TIMEOUT(513),
    UNKNOWN(-1)
}