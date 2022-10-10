package com.ducpv.repository

/**
 * Created by pvduc9773 on 12/08/2022.
 */
sealed class Result<out T> {
    class Success<T>(val value: T?) : Result<T>()
    class Error(val code: Int = -1, val message: String? = null) : Result<Nothing>()
}

enum class ErrorType(val code: Int) {
    UNKNOWN(-1),
    GENERIC(404),
    TIMEOUT(408),
    NETWORK(511)
}
