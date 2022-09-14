package com.noox.rickandmorty.core.api

import android.util.Log

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (ex: Exception) {
        Log.e("API CALL", ex.message, ex)
        Result.failure(ex)
    }
}
