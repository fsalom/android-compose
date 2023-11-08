package com.example.learnwithme.data.manager.network

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

interface  NetworkInterface {
    suspend fun <T> load(call: suspend () -> Response<T>): T
}
class NetworkManager {
    suspend fun <T> load(call: suspend () -> Response<T>): T {
        try {
            val response = call()
            response.body()?.let { body ->
                if (response.isSuccessful) {
                    return body
                } else {
                    throw response.code().toHttpError()
                }
            } ?: throw RetrofitError.EmptyBody

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val body = e.response()?.errorBody().toString()
                    throw RetrofitError.HttpException(body)
                }
                is SocketTimeoutException -> throw RetrofitError.Timeout("Timeout Error")
                is IOException -> throw RetrofitError.Network("Thread Error")
                else -> throw RetrofitError.Unknown("Unknown Error")
            }
        }
    }

    fun Int.toHttpError() =
        when (this) {
            204 -> HttpCodeError.ServerNoContent
            400 -> HttpCodeError.BadRequest
            401 -> HttpCodeError.Unauthorized
            403 -> HttpCodeError.Forbidden
            404 -> HttpCodeError.NotFound
            406 -> HttpCodeError.NotAcceptable
            408 -> HttpCodeError.Timeout
            409 -> HttpCodeError.ServerConflict
            else -> HttpCodeError.InternalServerError
        }
}