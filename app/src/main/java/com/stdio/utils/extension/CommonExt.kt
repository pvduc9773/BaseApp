package com.stdio.utils.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stdio.repository.ErrorCode
import com.stdio.repository.Response
import com.stdio.repository.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by pvduc9773 on 08/08/2022.
 */
inline fun <reified T> Gson.fromJson(json: String?): T? {
    val type = object : TypeToken<T>() {}.type
    return fromJson(json, type)
}

inline fun <T, R> T.runCatching(block: T.() -> Response<R>): Result<R> {
    return try {
        val response = block()
        if (response.isSuccessful) {
            Result.Success(response.data)
        } else {
            val errorResult = response.toErrorResult()
            errorResult
        }
    } catch (ex: Exception) {
        val errorResult = ex.toErrorResult()
        errorResult
    }
}

fun Exception.toErrorResult() =
    when (this) {
        is HttpException -> {
            val errorBody = response()?.errorBody()?.string()
            val response = Gson().fromJson<Response<Nothing>>(errorBody)
            Result.Error(
                code = response?.code ?: code(),
                message = response?.message ?: message()
            )
        }
        is IOException -> Result.Error(
            code = ErrorCode.NETWORK.code,
            message = message
        )
        is SocketTimeoutException -> Result.Error(
            code = ErrorCode.TIMEOUT.code,
            message = message
        )
        else -> Result.Error(
            code = ErrorCode.UNKNOWN.code,
            message = message
        )
    }

fun <T> Response<T>.toErrorResult() =
    Result.Error(
        code = code,
        message = message
    )