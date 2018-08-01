package com.ausichenko.cashflow.data.models

//todo: add status (success, error, loading), data, message
sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: Throwable) : Result<Nothing>()
}