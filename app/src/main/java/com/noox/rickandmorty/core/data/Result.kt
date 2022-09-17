package com.noox.rickandmorty.core.data

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: Throwable) : Result<Nothing>()

    val isFailure get() = this is Failure
    val isSuccess get() = this is Success

    inline fun <R> map(fn: (T) -> R): Result<R> = when (this) {
        is Failure -> Failure(error)
        is Success -> Success(fn(data))
    }

    inline fun fold(onFailure: (Throwable) -> Unit, onSuccess: (T) -> Unit) = when (this) {
        is Failure -> onFailure(error)
        is Success -> onSuccess(data)
    }
}
