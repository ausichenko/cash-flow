package com.ausichenko.cashflow.data.exeptions

open class DataException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataException("Data not found in remote data source")

class LocalDataNotFoundException : DataException("Data not found in local data source")