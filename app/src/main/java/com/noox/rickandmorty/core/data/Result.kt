package com.noox.rickandmorty.core.data

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    object Error : Result<Nothing>()
}
