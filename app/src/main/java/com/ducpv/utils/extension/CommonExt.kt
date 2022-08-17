package com.ducpv.utils.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ducpv.repository.ErrorType
import com.ducpv.repository.Response
import com.ducpv.repository.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by pvduc9773 on 08/08/2022.
 */
inline fun <reified T> Gson.fromJson(json: String?): T? = fromJson(json, object : TypeToken<T>() {}.type)

inline fun <T, R> T.executeWithCatchingThrows(block: T.() -> Response<R>): Result<R> =
    try {
        val response = block()
        if (response.isSuccessful) {
            Result.Success(
                value = response.data
            )
        } else {
            Result.Error(
                code = response.code,
                message = response.message
            )
        }
    } catch (e: Exception) {
        when (e) {
            is HttpException -> {
                val errorBody = e.response()?.errorBody()?.string()
                val response = Gson().fromJson<Response<Nothing>>(errorBody)
                Result.Error(
                    code = response?.code ?: e.code(),
                    message = response?.message ?: e.message()
                )
            }
            is SocketTimeoutException -> Result.Error(
                code = ErrorType.TIMEOUT.code,
                message = e.message
            )
            is IOException -> Result.Error(
                code = ErrorType.NETWORK.code,
                message = e.message
            )
            else -> Result.Error(
                code = ErrorType.UNKNOWN.code,
                message = e.message
            )
        }
    }